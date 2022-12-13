package pt.isec.pa.apoio_poe.model.data;


import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.alunodata.ClassificacaoComparator;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.*;


import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class GestaoEstagioData implements Serializable {

    private List<Aluno> alunos;
    private List<Aluno> alunosSemAtribuicao;
    private List<Docente> docentes;
    private List<Propostas> propostas;
    private List<Candidaturas> candidaturas;
    private Set<String> codsCandidatura;
    private Map<Long, List<String>> mapaCandidaturas;
    private Map<Propostas, Aluno> mapaPropostasAtribuidas;
    private Map<Propostas, Docente> mapaDocentesAtribuidos;
    private Map<Aluno, Integer> mapaPreferencia;
    private Map<Docente, List<String>> mapanOrientacoes;
    private List<Boolean> phaseLocks;// 0 primeira, 1 segunda, 2 terceira, 3 quarta


    public GestaoEstagioData() {
        initGame();
    }

    public void initGame() {
        alunos = new ArrayList<>();
        alunosSemAtribuicao = new ArrayList<>();
        docentes = new ArrayList<>();
        propostas = new ArrayList<>();
        candidaturas = new ArrayList<>();
        codsCandidatura = new HashSet<>();
        mapaCandidaturas = new HashMap<>();
        mapaPropostasAtribuidas = new HashMap<>();
        mapaDocentesAtribuidos = new HashMap<>();
        mapaPreferencia = new HashMap<>();
        mapanOrientacoes = new HashMap<>();
        phaseLocks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            phaseLocks.add(false);
        }
    }

    public boolean importaAlunos(String filename) throws Exception {
        List<Aluno> listaAlunos = new ArrayList<>();
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(filename);
        } catch (Exception e) {
            throw new Exception("Erro ao localizar ficheiro");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        //Read File Line By Line
       aqui: while ((strLine = br.readLine()) != null) {
            // Print the content on the console - do what you want to do
            String[] tokens = strLine.split(",");
            if(tokens[0].length() != 10) continue ;
            Aluno a = new Aluno(Long.parseLong(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], Double.parseDouble(tokens[5]), Boolean.parseBoolean(tokens[6]));
           String res =""+a.getClassificacao();String[] cursos = {"LEI", "LEI-PL"};String[] ramos = {"DA", "SI", "RAS"};
           if (!isValid(a.getEmailAluno()) || !containsWords(a.getSiglaCurso(),cursos) ||
                   !containsWords(a.getSiglaRamo(),ramos) ||
                   !hasChar(res))
               continue aqui;

            for (Aluno a1:listaAlunos) {
                if (a1.getId() == a.getId())
                    continue aqui;
            }
            listaAlunos.add(a);
        }
        //Close the input stream
        fstream.close();
        if(listaAlunos.isEmpty()) return false;
        for (Aluno a : listaAlunos) alunosSemAtribuicao.add(a);
        this.alunos = listaAlunos;
        return true;
    }

    public boolean importaDocentes(String filename) throws Exception {
        List<Docente> listaDocentes = new ArrayList<>();
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(filename);
        } catch (Exception e) {
            throw new Exception("Erro ao localizar ficheiro");
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        aqui:while ((strLine = br.readLine()) != null) {
            // Print the content on the console - do what you want to do
            String[] tokens = strLine.split(",");
            if(tokens[0].length()<4)
                continue ;

            Docente d = new Docente(
                    tokens[0], tokens[1]);
            if(!isValid(d.getEmailDocente())) continue ;

            for (Docente d1:listaDocentes)
                if (d1.getEmailDocente().equals(d.getEmailDocente()))
                    continue aqui;


            listaDocentes.add(d);
        }

        //Close the input stream
        fstream.close();
        if(listaDocentes.isEmpty()) return false;

        this.docentes = listaDocentes;

        return true;
    }


    public boolean importaCandidaturas(String filename) throws Exception {
        if(propostas.isEmpty()) return false;

        List<Candidaturas> listaCandidaturas = new ArrayList<>();
        FileInputStream fstream;
        try {
            fstream = new FileInputStream(filename);
        } catch (Exception e) {
            throw new Exception("Erro ao localizar ficheiro");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        List<String> aux = new ArrayList<>();

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            String[] tokens = strLine.split(",");
            if(containsID(tokens[0]) || verificaCand(listaCandidaturas,tokens[0]) || !tokens[0].startsWith("20")) continue;
             for (int i = 1; i < tokens.length; i++) {
                if(containsAluno(tokens[i])) continue;
                aux.add(tokens[i]);
                codsCandidatura.add(tokens[i]);
            }
            if(aux.isEmpty()) continue;
            Candidaturas c = new Candidaturas(Long.parseLong(tokens[0]), aux);
            listaCandidaturas.add(c);
            mapaCandidaturas.put(Long.parseLong(tokens[0]), aux);
            aux = new ArrayList<>();
        }

        //Close the input stream
        fstream.close();
        candidaturas.addAll(listaCandidaturas);


        return true;
    }


    public boolean importaPropostas(String filename) throws Exception {
        if(docentes.isEmpty()) return false;
        List<Propostas> listaPropostas = new ArrayList<>();
        FileInputStream fstream;

        try {
            fstream = new FileInputStream(filename);
        } catch (Exception e) {
            throw new Exception("Erro ao localizar ficheiro");
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console - do what you want to do
            String[] tokens = strLine.split(",");
            if(tokens[0].length()>2) continue;
            if (tokens[0].equals("T1")) {
                Estagio e;
                if (tokens.length == 5) {
                    e = new Estagio(tokens[1], tokens[2], tokens[3], tokens[4]);
                    if(verificacodProposta2(listaPropostas,e.getCodProposta()) || !verificaRamo(e.getSiglaRamoEstagio())){
                        continue;
                    }

                    listaPropostas.add(e);
                } else if (tokens.length == 6) {
                    e = new Estagio(tokens[1], tokens[2], tokens[3], tokens[4], Long.parseLong(tokens[5]));
                    if(verificacodProposta2(listaPropostas,e.getCodProposta()) || verificaAlunoProposta(listaPropostas,e.getNrAluno())
                    || !verificaRamo(e.getSiglaRamoEstagio())){
                        continue;
                    }

                    listaPropostas.add(e);
                }
            }
            if (tokens[0].equals("T2")) {
                Projeto pj;
                if (tokens.length == 5) {
                    pj = new Projeto(tokens[1], tokens[2], tokens[3], tokens[4]);
                    if(verificaDocente(pj.getEmailDocente())==null || verificacodProposta2(listaPropostas,pj.getCodProposta())
                    || !verificaRamo(pj.getSiglaRamo())) continue;

                    listaPropostas.add(pj);
                } else if (tokens.length == 6) {
                    pj = new Projeto(tokens[1], tokens[2], tokens[3], tokens[4], Long.parseLong(tokens[5]));
                    if(verificaDocente(pj.getEmailDocente())==null || verificacodProposta2(listaPropostas,pj.getCodProposta())
                    || verificaAlunoProposta(listaPropostas,pj.getNrAluno()) || !verificaRamo(pj.getSiglaRamo())) continue;
                    listaPropostas.add(pj);
                }
            }
            if (tokens[0].equals("T3")) {
                ProjetoAutoProposto ap = new ProjetoAutoProposto(tokens[1], tokens[2], Long.parseLong(tokens[3]));
                if(verificacodProposta2(listaPropostas,ap.getCodProposta()) || verificaAlunoProposta(listaPropostas,ap.getNrAluno())) continue;
                listaPropostas.add(ap);
            }
        }
        fstream.close();
        if(listaPropostas.isEmpty()) return false;
        this.propostas = listaPropostas;

        return true;
    }
    private boolean verificaRamo(String ramo){
        if(ramo.length()>3 && !ramo.contains("|"))
            return false;

        return true;
    }
    private boolean verificaCand(List<Candidaturas> cand,String id){
        long nr = Long.parseLong(id);
        for (Candidaturas c: cand)
            if(c.getNrAluno() == nr) return true;

        return false;
    }

    private boolean containsAluno(String cod){
        for (Propostas p:propostas)
            if(p.getCodProposta().equals(cod) && p.getNrAluno() != 0) return true;

        return false;
    }

    private boolean containsID(String id){
        long nr = Long.parseLong(id);
        for (Propostas p:propostas)
            if(p.getNrAluno() == nr) return true;


        return false;
    }

    private boolean containsWords(String inputString, String[] items) {
        boolean found = false;
        for (String item : items) {
            if (inputString.equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    private boolean hasChar(String classificacao){
            if(!Character.isDigit(classificacao.charAt(1)))
                return true;

        return false;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Aluno getAluno(long id) {
        for (Aluno a : alunos) {
            if (id == a.getId()) {
                return a;
            }
        }
        return null;
    }


    public List<Docente> getDocentes() {
        return docentes;
    }

    public List<Propostas> getPropostas() {
        return propostas;
    }

    public Propostas getProposta(String codProposta) {
        for (Propostas p : propostas) {
            if (codProposta.equals(p.getCodProposta())) {
                return p;
            }
        }
        return null;
    }

    public List<Candidaturas> getCandidaturas() {
        return candidaturas;
    }

    public boolean verificaIdAluno(Long id) {
        boolean existe = false;
        for (Aluno a : getAlunos()) {
            if (a.getId() == id) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public Boolean getAcesso(Long id) {
        if (verificaIdAluno(id)) {
            Aluno aluno = getAluno(id);
            return aluno.getAcesso();
        }
        return false;
    }


    public boolean inserirAluno(Long id, String nome, String email, String curso, String ramo, Double classificacao, String podeEstagio) {
        String[] nomes = nome.split(" ");
        if (String.valueOf(id).length() == 10 &&
                !verificaIdAluno(id) &&
                nomes.length == 2 && !nomes[0].matches(".*\\d.*") && !nomes[1].matches(".*\\d.*") &&
                isValid(email) &&
                (curso.equals("LEI") || curso.equals("LEI-PL") &&
                        ramo.equals("RAS") || ramo.equals("SI") || ramo.equals("DA") &&
                        (classificacao >= 0 || classificacao <= 1) && podeEstagio.equals("true"))) {
            Aluno novoAluno = new Aluno(id, nome, email, curso, ramo, classificacao, true);
            alunos.add(novoAluno);
            return true;
        } else if (String.valueOf(id).length() == 10 &&
                !verificaIdAluno(id) &&
                nomes.length == 2 && !nomes[0].matches(".*\\d.*") && !nomes[1].matches(".*\\d.*") &&
                isValid(email) &&
                (curso.equals("LEI") || curso.equals("LEI-PL") &&
                        ramo.equals("RAS") || ramo.equals("SI") || ramo.equals("DA") &&
                        (classificacao >= 0 || classificacao <= 1) && podeEstagio.equals("false"))) {
            Aluno novoAluno = new Aluno(id, nome, email, curso, ramo, classificacao, false);
            alunos.add(novoAluno);
            return true;
        }
        return false;
    }

    public static boolean isValid(String email) {
        if (email == null)
            return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public boolean editarNomeAluno(Long id, String nome) {
        String[] nomes = nome.split(" ");
        if (verificaIdAluno(id) && nomes.length == 2 && !nomes[0].matches(".*\\d.*") && !nomes[1].matches(".*\\d.*")) {
            getAluno(id).setNomeAluno(nome);
            return true;
        }
        return false;
    }

    public boolean editarEmailAluno(Long id, String email) {
        if (verificaIdAluno(id) && isValid(email)) {
            getAluno(id).setEmailAluno(email);
            return true;
        }
        return false;
    }

    public boolean editarRamoAluno(Long id, String ramo) {
        if (verificaIdAluno(id) && ramo.equals("RAS") || verificaIdAluno(id) && ramo.equals("SI") || verificaIdAluno(id) && ramo.equals("DA")) {
            getAluno(id).setSiglaRamo(ramo);
            return true;
        }
        return false;
    }

    public boolean editarCursoAluno(Long id, String curso) {
        if (verificaIdAluno(id) && curso.equals("LEI") || verificaIdAluno(id) && curso.equals("LEI-PL")) {
            getAluno(id).setSiglaCurso(curso);
            return true;
        }
        return false;
    }

    public boolean editarClassificacaoAluno(Long id, Double classficacao) {
        if (verificaIdAluno(id) && (classficacao >= 0 || classficacao <= 1)) {
            getAluno(id).setClassificacao(classficacao);
            return true;
        }
        return false;
    }

    public boolean editarAcessoAluno(Long id, String acesso) {
        if (verificaIdAluno(id) && acesso.equals("true")) {
            getAluno(id).setPodeEstagio(true);
            return true;
        } else if (verificaIdAluno(id) && acesso.equals("false")) {
            getAluno(id).setPodeEstagio(false);
            return true;
        }
        return false;
    }


    public boolean editarEmailDocente(String emailantigo, String emailnovo) {
        if (!verificaEmailDocente(emailnovo) && isValid(emailnovo)) {
            getDocente(emailantigo).setEmailDocente(emailnovo);
            return true;
        }

        return false;
    }

    public boolean editarNomeDocente(String email, String nome) {
        if (getDocente(email) == null) return false;

        getDocente(email).setNomeDocente(nome);

        return true;
    }


    public boolean verificaEmailDocente(String email) {
        boolean existe = false;
        for (Docente d : getDocentes()) {
            if (email.equals(d.getEmailDocente())) {
                existe = true;
                break;
            }
        }
        return existe;
    }

    public Docente getDocente(String email) {
        for (Docente d : getDocentes()) {
            if (email.equals(d.getEmailDocente())) {
                return d;
            }
        }
        return null;
    }

    public boolean insereDocente(String nome, String email) {
        String[] nomes = nome.split(" ");
        if (verificaDocente(email) == null && nomes.length == 2 && !nomes[0].matches(".*\\d.*") && !nomes[1].matches(".*\\d.*") &&
                isValid(email)) {
            Docente novoD = new Docente(nome, email);
            docentes.add(novoD);
            return true;
        }
        return false;
    }

    public boolean inserePropostas(Propostas p) {
        if (verificacodProposta(p.getCodProposta()) != null || !p.getCodProposta().startsWith("P") || p.getCodProposta().length() != 4 || getAluno(p.getNrAluno()) == null)
            return false;


        if (p instanceof Projeto && (((Projeto) p).getSiglaRamo().contains("RAS") || ((Projeto) p).getSiglaRamo().contains("SI") || ((Projeto) p).getSiglaRamo().contains("DA")) && isValid(((Projeto) p).getEmailDocente())) {
            propostas.add(p);
            return true;
        }
        if (p instanceof Estagio && (((Estagio) p).getSiglaRamoEstagio().contains("RAS") || ((Estagio) p).getSiglaRamoEstagio().contains("SI") || ((Estagio) p).getSiglaRamoEstagio().contains("DA"))) {
            propostas.add(p);
            return true;
        }
        if (p instanceof ProjetoAutoProposto) {
            propostas.add(p);
            return true;
        }

        return false;
    }

    public boolean editarProposta(String cod, String ramo, int op) {
        Propostas p = verificacodProposta(cod);
        if (p == null) return false;
        if (op == 1 && (ramo.contains("RAS") || ramo.contains("SI") || ramo.contains("DA"))) {
            if (p instanceof Estagio)
                ((Estagio) p).setSiglaRamoEstagio(ramo);
            if (p instanceof Projeto)
                ((Projeto) p).setSiglaRamo(ramo);
            return true;
        }
        if (op == 2) {
            p.setTitulo(ramo);
            return true;
        }
        if (p instanceof Estagio && op == 3) {
            ((Estagio) p).setInstituicaoEstagio(ramo);
            return true;
        }
        if (op == 4 && ramo.length() == 10) {
            long id = Long.parseLong(ramo);
            p.setNrAluno(id);
            return true;
        }
        if (op == 5 && isValid(ramo)) {
            assert p instanceof Projeto;
            ((Projeto) p).setEmailDocente(ramo);
            return true;
        }
        return false;
    }

    public boolean eliminarAluno(String id) {
        long idAux = Long.parseLong(id);
        boolean existe = false;
        for (Aluno a : getAlunos()) {
            if (a.getId() == idAux) {
                existe = true;
                getAlunos().remove(a);
                break;
            }
        }
        return existe;
    }

    public boolean eliminaDocente(String email) {
        boolean existe = false;
        for (Docente d : docentes) {
            if (email.equals(d.getEmailDocente())) {
                existe = true;
                docentes.remove(d);
                break;
            }
        }
        return existe;
    }

    public boolean eliminaProposta(String codProposta) {
        boolean elimina = false;
        for (Propostas p : getPropostas()) {
            if (codProposta.equals(p.getCodProposta())) {
                elimina = true;
                getPropostas().remove(p);
                break;
            }
        }
        return elimina;
    }

    private String verificaProposta(Propostas p) {
        if (p instanceof Estagio)
            return "T1";
        else if (p instanceof Projeto)
            return "T2";
        else if (p instanceof ProjetoAutoProposto)
            return "T3";
        return "null";
    }


    public boolean consultaAluno(String id) {
        long idAux = Long.parseLong(id);
        if (verificaIdAluno(idAux)) {
            System.out.println("Aluno: " + getAluno(idAux));
            return true;
        }
        return false;
    }

    public boolean consultarDocente(String email) {
        if (verificaEmailDocente(email)) {
            System.out.println("Docente: " + getDocente(email));
            return true;
        }
        return false;
    }

    public List<Aluno> listaAutoproposta() {
        List<Aluno> lista = new ArrayList<>();
        for (Candidaturas c : candidaturas) {
            for (Propostas p : propostas) {
                for (Aluno a : alunos) {
                    if (a.getId() == c.getNrAluno() && (verificaProposta(p).equals("T3") && p.getNrAluno() == c.getNrAluno())) {
                        lista.add(a);
                    }
                }
            }
        }
        return lista;
    }

    public List<Aluno> listaComCandidaturas() {
        List<Aluno> lista = new ArrayList<>();
        for (Candidaturas c : candidaturas) {
            for (Aluno a : alunos) {
                if (a.getId() == c.getNrAluno()) {
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    public List<Aluno> listaSemCandidaturas() {
        List<Aluno> lista = new ArrayList<>();
        for (Aluno a : alunos) {
            if (!mapaCandidaturas.containsKey(a.getId()))
                lista.add(a);
        }
        return lista;
    }

    public List<Propostas> listaAutopropostas() {
        List<Propostas> lista = new ArrayList<>();
        for (Propostas p : propostas) {
            if (verificaProposta(p).equals("T3"))
                lista.add(p);
        }
        return lista;
    }

    public List<Propostas> listaProjetos() {
        List<Propostas> lista = new ArrayList<>();
        for (Propostas p : propostas) {
            if (verificaProposta(p).equals("T2"))
                lista.add(p);
        }
        return lista;
    }

    public List<Propostas> listaPropComCand() {
        List<Propostas> lista = new ArrayList<>();
        for (Propostas p : propostas) {
            for (String s : codsCandidatura) {
                if (p.getCodProposta().equals(s)) {
                    lista.add(p);
                }
            }
        }
        return lista;
    }


    public List<Propostas> listaPropSemCand() {
        List<Propostas> lista = new ArrayList<>(propostas);

        Iterator<Propostas> i = lista.iterator();
        while (i.hasNext()) {
            Propostas el = i.next();
            for (String s : codsCandidatura) {
                if (el.getCodProposta().equals(s))
                    i.remove();
            }
        }
        return lista;
    }

    public boolean atribuiAutoProposta() {
        boolean atribui = false;
        for (Propostas p : propostas) {
            Iterator<Aluno> i = alunosSemAtribuicao.iterator();
            while (i.hasNext()) {
                Aluno a = i.next();
                if (a.getId() == p.getNrAluno() && p.getNrAluno() != 0 && !verificaProposta(p).equals("T1")) {
                    mapaPropostasAtribuidas.put(p, a);
                    mapaPreferencia.put(a, 1);
                    i.remove();
                    atribui = true;
                }
            }
        }
        return atribui;
    }


    public boolean atribuiPropostaDisp() {
        boolean atribui = false;
        if (alunosSemAtribuicao == null) // Como os alunos estão todos atribuidos não existem propostas disponíveis
            return false;
        Comparator<Aluno> compareA = new ClassificacaoComparator();
        alunosSemAtribuicao.sort(compareA);
        Iterator<Aluno> i = alunosSemAtribuicao.iterator();

        while (i.hasNext()) {
            Aluno asa = i.next(); // prox aluno
            if (mapaCandidaturas.containsKey(asa.getId())) { // aluno candidatou-se
                for (int j = 0; j < mapaCandidaturas.get(asa.getId()).size(); j++) { // percorre opcoes 1,2,3,4,5
                    if (!mapaPropostasAtribuidas.containsKey(getProposta(mapaCandidaturas.get(asa.getId()).get(j)))) { // se não estiver atribuido mete no mapa
                        mapaPropostasAtribuidas.put(getProposta(mapaCandidaturas.get(asa.getId()).get(j)), asa);
                        mapaPreferencia.put(asa, j + 1);
                        i.remove();
                        atribui = true;
                        break;
                    }
                }
            }
        }
        return atribui;
    }

    public boolean atribuiPropostaManual(long nrAluno, String codProposta) {
        //TODO BUG REDO POR CAUSA DO REMOVE
        Propostas p1 = verificacodProposta(codProposta);
        Aluno a1 = getAluno(nrAluno);
        if (p1 == null || a1 == null)
            return false;
        for (Propostas p : propostas) {
            for (Aluno a : alunosSemAtribuicao) {
                if ((a.getId() == nrAluno && p.getCodProposta().equals(codProposta)) && (!mapaPropostasAtribuidas.containsValue(a) && !mapaPropostasAtribuidas.containsKey(p))) {
                    mapaPropostasAtribuidas.put(p, a);
                    alunosSemAtribuicao.remove(a);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removePropostaManual(long nrAluno, String codProposta) {
        Propostas p1 = verificacodProposta(codProposta);
        Aluno a1 = getAluno(nrAluno);
        if (p1 == null || a1 == null || mapaPropostasAtribuidas.isEmpty())
            return false;


        if (mapaPropostasAtribuidas.containsValue(a1) && mapaPropostasAtribuidas.containsKey(p1)) {
            mapaPropostasAtribuidas.remove(p1, a1);
            alunosSemAtribuicao.add(a1);
            return true;
        }
        return false;
    }

    private Propostas verificacodProposta(String codProposta) {
        for (Propostas p : propostas) {
            if (p.getCodProposta().equals(codProposta)) {
                return p;
            }
        }
        return null;
    }

    private boolean verificacodProposta2(List<Propostas> lista,String codProposta) {
        for (Propostas p : lista) {
            if (p.getCodProposta().equals(codProposta)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaAlunoProposta(List<Propostas> lista,long id) {
        for (Propostas p : lista) {
            if (p.getNrAluno() == id || getAluno(id)==null) {
                return true;
            }
        }
        return false;
    }

    public List<Aluno> listaAlunosComAutoProp() {
        List<Aluno> lista = new ArrayList<>();
        for (Propostas p : propostas) {
            if (mapaPropostasAtribuidas.containsKey(p) && verificaProposta(p).equals("T3")) {
                lista.add(mapaPropostasAtribuidas.get(p));
            }
        }
        return lista;

    }


    public List<Aluno> listaAlunosComCand() {
        List<Aluno> lista = new ArrayList<>();
        for (Map.Entry<Propostas, Aluno> pair : mapaPropostasAtribuidas.entrySet()) {
            if (mapaCandidaturas.containsKey(pair.getValue().getId())) lista.add(pair.getValue());
        }
        return lista;
    }

    public Map<Aluno, Integer> listaPropostaAtribuida() {
        /*List<Aluno> lista = new ArrayList<>();
        Map<Aluno, Integer> mapa = new HashMap<>();
        for (Map.Entry<Propostas, Aluno> pair : mapaPropostasAtribuidas.entrySet()) {
            lista.add(pair.getValue());
        }*/
        return mapaPreferencia;
    }

    public List<Aluno> listaAlunosComProposta() {
        List<Aluno> lista = new ArrayList<>();
        for (Propostas p : propostas) {
            if (mapaPropostasAtribuidas.containsKey(p)) {
                lista.add(mapaPropostasAtribuidas.get(p));

            }
        }
        return lista;
    }

    public List<Aluno> listaAlunosSemAtribuicao() {
        return alunosSemAtribuicao;
    }

    public List<Propostas> listaPropostasDisp() {
        List<Propostas> lista = new ArrayList<>();
        for (Propostas p : propostas) {
            if (!mapaPropostasAtribuidas.containsKey(p)) {
                lista.add(p);
            }
        }
        return lista;
    }

    private boolean verificaCandidatura(Long id) {
        for (Candidaturas c : candidaturas) {
            if (id == c.getNrAluno()) break;
        }
        return false;
    }

    public List<Candidaturas> listaCandidaturas() {
        return candidaturas;
    }

    public boolean insereCandidatura(Long id, String propostas) {
        Aluno a = getAluno(id);
        if (a == null) return false;

        String[] tokens = propostas.split(" ");
        if (tokens.length <= 1)
            return false;
        List<String> cand = new ArrayList<>(Arrays.asList(tokens));
        for (String s : cand)
            if (verificacodProposta(s) == null) return false;

        if (String.valueOf(id).length() == 10 && !verificaCandidatura(id)) {
            Candidaturas novoC = new Candidaturas(id, cand);
            candidaturas.add(novoC);
            return true;
        }
        return false;
    }

    public List<Propostas> listaPropostasAtribuidas() {
        List<Propostas> lista = new ArrayList<>();
        for (Map.Entry<Propostas, Aluno> pair : mapaPropostasAtribuidas.entrySet()) {
            lista.add(pair.getKey());
        }
        return lista;
    }

    public boolean atribuiProponentesProjeto() {
        boolean atribuido = false;
        for (Propostas p : propostas) {
            if (verificaProposta(p).equals("T2")) {
                Docente d = verificaDocente(((Projeto) p).getEmailDocente());
                if (d != null) {
                    mapaDocentesAtribuidos.put(p, d);
                    atribuido = true;
                }
            }
        }
        return atribuido;
    }

    public boolean alteraProponente(String cod, String email) {
        if (verificaDocente(email) == null && verificacodProposta(cod) == null && (verificaEmailDocente(email) && mapaDocentesAtribuidos.containsValue(verificaDocente(email))))
            return false;

        mapaDocentesAtribuidos.replace(verificacodProposta(cod), verificaDocente(email));
        return true;
    }

    public List<Docente> consultaProponente() {
        List<Docente> lista = new ArrayList<>();
        for (Map.Entry<Propostas, Docente> pair : mapaDocentesAtribuidos.entrySet()) {
            lista.add(pair.getValue());
        }
        return lista;
    }

    public boolean atribuiProponenteManual(String cod, String email) {
        Docente d = verificaDocente(email);
        Propostas p = verificacodProposta(cod);
        if ((d == null || p == null) || !verificaProposta(p).equals("T2"))
            return false;

        if (!mapaDocentesAtribuidos.containsValue(p)) {
            mapaDocentesAtribuidos.put(p, d);
            return true;
        }
        for (Map.Entry<Propostas, Docente> pair : mapaDocentesAtribuidos.entrySet()) {
            if (pair.getValue() != d && pair.getKey() == p) {
                mapaDocentesAtribuidos.replace(p, d);
                return true;
            }
        }

        return false;
    }

    public boolean removeProponenteManual(String cod, String email) {
        Docente d = verificaDocente(email);
        Propostas p = verificacodProposta(cod);
        if ((d == null || p == null || mapaDocentesAtribuidos.isEmpty()) || !verificaProposta(p).equals("T2"))
            return false;

        for (Map.Entry<Propostas, Docente> pair : mapaDocentesAtribuidos.entrySet()) {
            if (pair.getValue() == d && pair.getKey() == p) {
                mapaDocentesAtribuidos.replace(p, null);
                return true;
            }
        }

        return false;
    }


    private Docente verificaDocente(String email) {
        for (Docente d : docentes) {
            if (d.getEmailDocente().equals(email))
                return d;
        }
        return null;
    }

    public List<Aluno> listaAlunoComOrientador() {
        List<Aluno> laco = new ArrayList<>();
        for (Propostas p : propostas) {
            if (mapaPropostasAtribuidas.containsKey(p) && mapaDocentesAtribuidos.containsKey(p)) {
                laco.add(mapaPropostasAtribuidas.get(p));
            }
        }
        return laco;
    }

    public List<Aluno> listaAlunoSemOrientador() {
        List<Aluno> laso = new ArrayList<>();
        for (Propostas p : propostas) {
            if (mapaPropostasAtribuidas.containsKey(p) && !mapaDocentesAtribuidos.containsKey(p)) {
                laso.add(mapaPropostasAtribuidas.get(p));
            }
        }
        return laso;
    }

    public List<String> contaOrientacoes() {
        List<String> list = new ArrayList<>();
        for (Docente d : docentes) {
            for (Propostas p : propostas) {
                if (d.equals(mapaDocentesAtribuidos.get(p))) {
                    list.add(p.getCodProposta());
                }
            }
            mapanOrientacoes.put(d, list);
            list = new ArrayList<>();
        }

        return list;
    }


    public Map<Docente, List<String>> listanOrientacoes() {
        return mapanOrientacoes;
    }

    public List<String> getOrientadores(){
        List<String> list = new ArrayList<>();
        for (Map.Entry<Docente, List<String>> pair : mapanOrientacoes.entrySet()) {
            list.add(pair.getKey().getNomeDocente());
        }
        return list;
    }

    public int getOrientacaoDocente(String doc){
        int orientacoes=0;
        for (Map.Entry<Docente, List<String>> pair : mapanOrientacoes.entrySet()) {
            if(pair.getKey().getNomeDocente().equals(doc)) orientacoes = pair.getValue().size();
        }
        return orientacoes;
    }

    public double getMediaOrientacao() {
        double media=0,contador=0;
        for (Map.Entry<Docente, List<String>> pair : mapanOrientacoes.entrySet()) {
            media+=pair.getValue().size();
            contador++;
        }
        media = media /contador;
        return media;
    }
    public int getMinimoOrientacao() {
        int minimo=0;
        for (Map.Entry<Docente, List<String>> pair : mapanOrientacoes.entrySet()) {
            if(pair.getValue().size() < minimo)
                minimo=pair.getValue().size();
        }
        return minimo;
    }
    public int getMaximaOrientacao() {
        int maximo=0;
        for (Map.Entry<Docente, List<String>> pair : mapanOrientacoes.entrySet()) {
            if(pair.getValue().size() > maximo)
                maximo=pair.getValue().size();
        }
        return maximo;
    }

    public List<Aluno> listaAlunosComCandSemProp() {
        List<Aluno> lista = new ArrayList<>();
        for (Aluno a : alunos) {
            if (mapaCandidaturas.containsKey(a.getId()) && !mapaPropostasAtribuidas.containsValue(a)) {
                lista.add(a);
            }
        }
        return lista;
    }

    public void exportaCsvAlunos(String filename) {
        FileWriter file;
        try {
            file = new FileWriter(filename);
            //Iterate through bookList
            for (Aluno a : alunos) {
                file.append(String.format("%d,%s,%s,%s,%s,", a.getId(), a.getNomeAluno(), a.getEmailAluno(), a.getSiglaCurso(), a.getSiglaRamo()));
                file.append(String.format("%.3f", a.getClassificacao()));
                file.append(String.format(",%b\n", a.getAcesso()));
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportaCsvCandidaturas(String filename) {
        FileWriter file;
        try {
            file = new FileWriter(filename);
            //Iterate through bookList
            for (Candidaturas c : candidaturas) {
                file.append(String.format("%d", c.getNrAluno()));
                for (String s : c.getCandidaturas())
                    file.append(",").append(s);

                file.append("\n");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exportaCsvDocentes(String filename) {
        FileWriter file;
        try {
            file = new FileWriter(filename);
            //Iterate through bookList
            for (Docente d : docentes) {
                file.append(d.getNomeDocente()).append(",").append(d.getEmailDocente()).append("\n");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportaCsvPropostas(String filename) {
        FileWriter file;
        try {
            file = new FileWriter(filename);
            //Iterate through bookList
            for (Propostas p : propostas) {
                if (p instanceof Estagio) {
                    if (p.getNrAluno() != 0)
                        file.append(String.format("T1,%s,%s,%s,%s,%d\n", p.getCodProposta(), p.getTitulo(), ((Estagio) p).getSiglaRamoEstagio(),
                                ((Estagio) p).getInstituicaoEstagio(), p.getNrAluno()));
                    else
                        file.append(String.format("T1,%s,%s,%s,%s\n", p.getCodProposta(), p.getTitulo(), ((Estagio) p).getSiglaRamoEstagio(),
                                ((Estagio) p).getInstituicaoEstagio()));
                }
                if (p instanceof Projeto) {
                    if (p.getNrAluno() != 0)
                        file.append(String.format("T2,%s,%s,%s,%s,%d\n", p.getCodProposta(), p.getTitulo(), ((Projeto) p).getSiglaRamo(),
                                ((Projeto) p).getEmailDocente(), p.getNrAluno()));
                    else
                        file.append(String.format("T2,%s,%s,%s,%s\n", p.getCodProposta(), p.getTitulo(), ((Projeto) p).getSiglaRamo(),
                                ((Projeto) p).getEmailDocente()));

                }
                if (p instanceof ProjetoAutoProposto) {
                    file.append(String.format("T3,%s,%s,%d\n", p.getCodProposta(), p.getTitulo(), p.getNrAluno()));
                }

            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int retornaOrdem(Aluno a, Propostas p) {
        for (Candidaturas c : candidaturas) {
            if (a.getId() == c.getNrAluno())
                for (int i = 0; i < c.getCandidaturas().size(); i++) {
                    if (c.getCandidaturas().get(i).equals(p.getCodProposta()))
                        return i + 1;
                }
        }
        return 0;
    }


    public void exportaCsvFase3(String filename) throws Exception {

        FileWriter file;
        try {
            file = new FileWriter(filename);
            //Iterate through bookList
            for (Map.Entry<Propostas, Aluno> pair : mapaPropostasAtribuidas.entrySet()) {
                file.append(pair.getKey().getCodProposta()).append(",").append(String.valueOf(pair.getValue().getId())).append("\n");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportaCsvFase4(String filename) throws Exception {
        FileWriter file;
        try {
            file = new FileWriter(filename);
            //Iterate through bookList
            for (Map.Entry<Propostas, Docente> pair : mapaDocentesAtribuidos.entrySet()) {
                file.append(pair.getKey().getCodProposta()).append(",").append(pair.getValue().getEmailDocente()).append("\n");
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean alunosPorRamo() {
        Map<String, List<Aluno>> mapaAlunosPerRamo = new HashMap<>();
        List<Propostas> listaDa = new ArrayList<>(); // lista propostas DA
        List<Propostas> listaSr = new ArrayList<>(); // lista propostas RAS
        List<Propostas> listaSi = new ArrayList<>(); // lista propostas SI
        List<Aluno> auxAlunoDa = new ArrayList<>(); // lista alunos DA
        List<Aluno> auxAlunoSr = new ArrayList<>(); // lista alunos RAS
        List<Aluno> auxAlunoSi = new ArrayList<>(); // lista alunos SI

        for (Aluno a : alunos) {
            if (a.getSiglaRamo().equals("DA"))
                auxAlunoDa.add(a);
            if (a.getSiglaRamo().equals("SI"))
                auxAlunoSi.add(a);
            if (a.getSiglaRamo().equals("RAS"))
                auxAlunoSr.add(a);
        }

        mapaAlunosPerRamo.put("DA", auxAlunoDa);
        mapaAlunosPerRamo.put("SI", auxAlunoSi);
        mapaAlunosPerRamo.put("RAS", auxAlunoSr);
        for (Propostas p : propostas) {
            String s = "";
            if (p instanceof Projeto)
                s = ((Projeto) p).getSiglaRamo();
            else if (p instanceof Estagio)
                s = ((Estagio) p).getSiglaRamoEstagio();
            if (!(p instanceof ProjetoAutoProposto)) {
                List<String> tokens = List.of(s.split("\\|"));
                for (String token : tokens) { //tem mais que um ramo
                    if (token.equals("DA")) {
                        listaDa.add(p);
                    }
                    if (token.equals("RAS")) {
                        listaSr.add(p);
                    }
                    if (token.equals("SI")) {
                        listaSi.add(p);
                    }
                }
            }
        }

        return mapaAlunosPerRamo.get("DA").size() <= listaDa.size() && mapaAlunosPerRamo.get("SI").size() <= listaSi.size()
                && mapaAlunosPerRamo.get("RAS").size() <= listaSr.size() && listaDa.size() > 0 && mapaAlunosPerRamo.get("DA").size() > 0;
    }

    private boolean alunosCandidatosAtribuidos() {
        if (candidaturas == null || listaAlunosComCandSemProp().isEmpty()) {
            return true;
        }

        int i = 0;
        for (Aluno a : alunos) {
            if (mapaPreferencia.containsKey(a))
                i++;
        }

        return i == alunos.size();
    }

    public boolean fecharFaseConfig() {
        if (alunosPorRamo()) {
            phaseLocks.set(0, true);
            return true;
        }
        return false;
    }

    public boolean fecharFaseCandidatura() {
        if (getPhaseLocks(0)) {
            phaseLocks.set(1, true);
            return true;
        }
        return false;
    }

    public boolean fecharFaseAtribuiPropostas() {
        if (alunosCandidatosAtribuidos()) {
            phaseLocks.set(2, true);
            return true;
        }
        return false;
    }

    public boolean fecharFaseOrientador() {
        phaseLocks.set(3, true);
        return true;
    }

    public Boolean getPhaseLocks(int index) {
        return phaseLocks.get(index);
    }

    public boolean listaEstagio(String cod) {
        for (Propostas p : propostas) {
            if (verificaProposta(p).equals("T1") && p.getCodProposta().equals(cod)) {
                System.out.println(p);
                return true;
            }
        }
        return false;
    }

    public boolean listaAutoProposto(String cod) {
        for (Propostas p : propostas) {
            if (verificaProposta(p).equals("T3") && p.getCodProposta().equals(cod)) {
                System.out.println(p);
                return true;
            }
        }
        return false;
    }

    public List<Propostas> listaProjetoAtribuido() {
        List<Propostas> lista = new ArrayList<>();
        for (Map.Entry<Propostas, Aluno> pair : mapaPropostasAtribuidas.entrySet()) {
            if (pair.getKey() instanceof Projeto)
                lista.add(pair.getKey());
        }
        return lista;
    }

    public boolean listaProjeto(String cod) {
        for (Propostas p : propostas) {
            if (verificaProposta(p).equals("T2") && p.getCodProposta().equals(cod)) {
                System.out.println(p);
                return true;
            }
        }
        return false;
    }

    public List<Aluno> listaAlunos() {
        return alunos;
    }

    public List<Propostas> listaPropostas() {
        return propostas;
    }

    public List<Docente> listaDocentes() {
        return docentes;
    }
}
