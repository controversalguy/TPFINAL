package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.io.*;
import java.util.List;
import java.util.Map;

import static pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioState.INICIO;

public class GestaoEstagioContext implements Serializable {
    private IGestaoEstagioState state;
    GestaoEstagioData data;
    CommandManager cm;
    private static final String FILENAME = "file.bin";

    public GestaoEstagioContext() {
        data = new GestaoEstagioData();
        state = INICIO.createState(this, data);
        cm = new CommandManager();
    }

    public GestaoEstagioState getState() {
        if (state == null) return null;
        return state.getState();
    }

    public void start() {
        state.start();
    }
    public boolean recebeModo(int op) {
        return state.recebeModo(op);
    }


    public boolean inserirAluno(Long id, String nome, String email, String curso, String ramo, Double classificacao, String podeEstagio) {
        return state.inserirAluno(id, nome, email, curso, ramo, classificacao, podeEstagio);
    }

    public Aluno getAluno(String id){
        return state.getAluno(id);
    }

    public boolean eliminarAluno(long id) {
        return state.eliminar(String.valueOf(id));
    }

    public boolean editarProposta(String cod,String dados,int op){
        return state.editarProposta(cod,dados,op);
    }
    public Propostas getProposta(String text) {
        return state.getProposta(text);
    }
/*
    public boolean consultarAluno(int id) {
        return state.consultarAluno(id);
    }*/
    /* public boolean editarAlunoNome(Long id, String novoNome) {
        return state.editarAlunoNome(id, novoNome);
    }*/

    public boolean editarEmailAluno(Long id, String email) {
        return state.editarEmailAluno(id, email);
    }

    public boolean editarNomeAluno(Long id, String nome) {
        return state.editarNomeAluno(id, nome);
    }
    public boolean editarRamoAluno(Long id, String ramo) {
        return state.editarRamoAluno(id, ramo);
    }
    public boolean editarCursoAluno(Long id, String curso) {
        return state.editarCursoAluno(id, curso);
    }
    public boolean editarClassificacaoAluno(Long id, Double classificacao) {
        return state.editarClassificacaoAluno(id, classificacao);
    }
    public boolean editarAcessoAluno(Long id, String acesso) {
        return state.editarAcessoAluno(id, acesso);
    }
    /*public boolean editarAlunoCurso(Long id, String curso) {
        return state.editarAlunoCurso(id, curso);
    }

    public boolean editarAlunoRamo(Long id, String ramo) {
        return state.editarAlunoRamo(id, ramo);
    }

    public boolean editarAlunoClassificacao(Long id, double classificacao) {
        return state.editarAlunoClassificacao(id, classificacao);
    }

    public boolean editarAcessoEstagio(Long id, boolean access) {
        return state.editarAcessoEstagio(id, access);
    }*/

    public boolean verificaIdAluno(Long id) {
        return state.verificaIdAluno(id);
    }
    public Boolean getAcesso(String id) {return  state.getAcesso(id);}

    //Docentes
    public boolean inserirDocente(String nome, String email) {
        return state.inserirDocente(nome, email);
    }

    public Docente getDocente(String email){
        return state.getDocente(email);
    }

    public boolean verificaEmailDocente(String emailDocente) {
        return state.verificaEmailDocente(emailDocente);
    }

    /*
     public boolean editarDocenteNome(String email, String novoNome){
         return state.editarDocenteNome(email,novoNome);
     }*/
    public boolean editarEmailDocente(String emailantigo,String emailnovo) {
        return state.editarEmailDocente(emailantigo,emailnovo);
    }
    public boolean editarNomeDocente(String email, String nome){
        return state.editarNomeDocente(email,nome);
    }

    public boolean eliminarDocente(String email) {
        return state.eliminar(email);
    }

    // Propostas

    public boolean insereEstagio(String codProposta, String ramo, String titulo, String instituicao, long nrAluno) {
        return state.insereEstagio(codProposta, ramo, titulo, instituicao, nrAluno);
    }

    public boolean insereProjeto(String codProposta, String ramo, String titulo, String emailDocente, long nrAluno) {
        return state.insereProjeto(codProposta, ramo, titulo, emailDocente, nrAluno);
    }

    public boolean insereAutoproposto(String codProposta, String titulo, long nrAluno) {
        return state.insereAutoproposto(codProposta, titulo, nrAluno);
    }

    public boolean eliminaProposta(String codProposta) {
        return state.eliminar(codProposta);
    }

    public boolean consultarPropostas(String codProposta) {
        return state.consulta(codProposta);
    }

    public void estadoSeguinte(IGestaoEstagioState newState) {
        state = newState;
    }

    public void regressaFaseAnterior() {
        state.regressarFaseAnterior();
    }

    public boolean fecharFaseCandidatura() {
        return state.fecharFaseCandidatura();
    }

    public boolean fecharFaseAtribuiPropostas() {
        return state.fecharFaseAtribuiPropostas();
    }

    public boolean fecharFaseOrientador() {
        return state.fecharFaseOrientador();
    }

    public void avancarFase() {
        state.avancarFase();
    }

    public boolean consulta(String id) {
        return state.consulta(id);
    }


    public boolean consultaAutoProposto(String cod) {
        return state.consultaAutoProposto(cod);
    }

    public boolean consultaEstagio(String cod) {
        return state.consultaEstagio(cod);
    }

    public boolean consultaProjeto(String cod) {
        return state.consultaProjeto(cod);
    }

    public List<Docente> listaDocentes() {
        return state.listaDocentes();
    }

    public List<Candidaturas> consultaCandidaturas() {
        return state.consultaCandidaturas();
    }

    public List<Candidaturas> listaCandidaturasLock() {
        return state.listaCandidaturasLock();
    }

    public boolean insereCandidatura(Long id,String propostas){
        return state.insereCandidatura(id,propostas);
    }

    public boolean importaAlunos(String filename) throws Exception {
        return state.importaAlunos(filename);
    }


    public boolean importaDocentes(String filename) throws Exception {
        return state.importaDocentes(filename);
    }

    public List<Docente> consultaProponente(){
        return state.consultaProponente();
    }

    public boolean importaPropostas(String filename) throws Exception {
        return state.importaPropostas(filename);
    }

    public boolean importaCandidaturas(String filename) throws Exception {
        return state.importaCandidaturas(filename);
    }

    public List<Aluno> listaAutoproposta() {
        return state.listaAutoproposta();
    }

    public List<Aluno> listaComCandidaturas() {
        return state.listaCandidaturas();
    }

    public List<Aluno> listaSemCandidaturas() {
        return state.listaSemCandidaturas();
    }

    public List<Propostas> listaAutopropostas() {
        return state.listaAutopropostas();
    }

    public List<Propostas> listaProjetos() {
        return state.listaProjetos();
    }

    public List<Propostas> listaPropComCand() {
        return state.listaPropComCand();
    }

    public List<Propostas> listaPropSemCand() {
        return state.listaPropSemCand();
    }

    // atribuir

    public boolean atribuiAutoProposta() {
        return state.atribuiAutoProposta();
    }

    public boolean atribuiPropostaManual(long nrAluno, String codProposta) {
        //return state.atribuiPropostaManual(nrAluno, codProposta);
        return cm.invokeCommand(new AtribuiPropostaCommand(state,nrAluno,codProposta));
    }

    public boolean atribuiPropostaDisp() {
        return state.atribuiPropostaDisp();
    }

    public List<Aluno> listaAlunosComAutoProp() {
        return state.listaAlunosComAutoProp();
    }

    public List<Aluno> listaAlunosComCand() {
        return state.listaAlunosComCand();
    }

    public List<Propostas> listaProjetoAtribuido(){return state.listaProjetoAtribuido();}

    public Map<Aluno, Integer> listaPropostaAtribuida() {
        return state.listaPropostaAtribuida();
    }
    public List<Aluno> listaAlunosComProposta(){
        return state.listaAlunosComProposta();
    }
    public List<Propostas> propostasDisponiveis(){
        return state.propostasDisponiveis();
    }
    public List<Propostas>  propostasAtribuidas(){

        return state.propostasAtribuidas();
    }
    public Map<Docente, List<String>> orientacoesDocente(){
        return state.orientacoesDocente();
    }
    public List<String> getOrientadores(){return state.getOrientadores();}
    public double getMediaOrientacao(){return state.getMediaOrientacao();}
    public int getMinimoOrientacao(){return state.getMinimoOrientacao();}
    public int getMaximaOrientacao(){return state.getMaximaOrientacao();}
    public int getOrientacaoDocente(String doc){return state.getOrientacaoDocente(doc);}

    public boolean removePropostaManual(long nrAluno,String codProposta) {
        //return state.removePropostaManual(nrAluno,codProposta);
        return cm.invokeCommand(new RemovePropostaCommand(state,nrAluno,codProposta));
    }

    public List<Aluno> listaAlunosSemAtribuicao() {
       return state.listaAlunosSemAtribuicao();
    }

    public List<Propostas> listaPropostasDisp() {
        return state.listaPropostasDisp();
    }

    public List<Propostas> listaPropostasAtribuidas() {
       return state.listaPropostasAtribuidas();
    }

    public boolean atribuiProponentesProjeto() {
        return state.atribuiProponentesProjeto();
    }
    public boolean atribuiProponenteManual(String cod,String email){
        return cm.invokeCommand(new AtribuiProponenteCommand(state,cod,email));
    }
    public boolean removeProponenteManual(String cod,String email){
        return cm.invokeCommand(new RemoveProponenteCommand(state,cod,email));
    }
    public boolean alteraProponente(String cod,String email){
        return state.alteraProponente(cod,email);
    }

    public List<Aluno> listaAlunoComOrientador() {
        return state.listaAlunoComOrientador();
    }

    public List<Aluno> listaAlunoSemOrientador() {
        return state.listaAlunoSemOrientador();
    }

    public List<String> contaOrientacoes() {
        return state.contaOrientacoes();
    }

    public Map<Docente, List<String>> listanOrientacoes() {
        return state.listanOrientacoes();
    }

    public List<Aluno> listaAlunosComCandSemProp() {
       return state.listaAlunosComCandSemProp();
    }

   // public void load(String filename) throws Exception { // implementado parcialmente (impossível aceder às informações guardadas)
        /*try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            GestaoEstagioData newData = (GestaoEstagioData) ois.readObject();
            data = newData;
            state = data.getState().createState(this,newData);
            /*          System.out.println("data"+data.getDocentes());*/
        /*} catch (Exception e) {
            throw new Exception("Erro ao carregar a informação atual no sistema");
        }

    }*/

    /*public void save(String filename) throws Exception {
        data.saveState(state.getState());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (Exception e) {
            throw new Exception("Erro ao guardar a informação atual no sistema");
        }
    }*/

    public void exportaCsvAlunos(String filename) throws Exception {
        state.exportaCsvAlunos(filename);
    }

    public void exportaCsvDocentes(String filename) throws Exception {
        state.exportaCsvDocentes(filename);
    }

    public void exportaCsvPropostas(String filename) throws Exception {
        state.exportaCsvPropostas(filename);
    }

    public void exportaCsvCandidaturas(String filename) throws Exception {
        state.exportaCsvCandidaturas(filename);
    }

    public void exportaCsvFase3(String filename) throws Exception {
        state.exportaCsvFase3(filename);
    }

    public void exportaCsvFase4(String filename) throws Exception {
        state.exportaCsvFase4(filename);
    }


    public List<Aluno> listaAlunos() {
        return state.listaAlunos();
    }

    public List<Propostas> listaPropostas() {
        return state.listaPropostas();
    }

    public void consultaOrientadorAtribuido() {
        state.listaOrientadorAtribuido();
    }

    public boolean undo(){
        return cm.undo();
    }

    public boolean redo(){
        return cm.redo();
    }

}
