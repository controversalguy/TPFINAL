package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;
import java.util.Map;

public class GestaoEstagioManager {

    GestaoEstagioContext fsm;
    PropertyChangeSupport pcs;

    public GestaoEstagioManager() {
        fsm = new GestaoEstagioContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public GestaoEstagioState getState() {
        return fsm.getState();
    }

    public void start() {
        fsm.start();
        pcs.firePropertyChange(null, null, fsm.getState());
    }

    public boolean recebeModo(int op) {
        Boolean b = fsm.recebeModo(op);
        pcs.firePropertyChange(null, null, fsm.getState());
        return b;
    }

    public boolean inserirAluno(Long id, String nome, String email, String curso, String ramo, Double classificacao, String podeEstagio) {
        boolean t = fsm.inserirAluno(id, nome, email, curso, ramo, classificacao, podeEstagio);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean eliminarAluno(long id) {
        boolean t = fsm.eliminarAluno(id);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarProposta(String cod, String ramos, int op) {
        boolean t = fsm.editarProposta(cod, ramos, op);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarEmailAluno(Long id, String email) {
        boolean t = fsm.editarEmailAluno(id, email);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarNomeAluno(Long id, String nome) {
        boolean t = fsm.editarNomeAluno(id, nome);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarRamoAluno(Long id, String ramo) {
        boolean t = fsm.editarRamoAluno(id, ramo);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarCursoAluno(Long id, String curso) {
        boolean t = fsm.editarCursoAluno(id, curso);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarClassificacaoAluno(Long id, Double classificacao) {
        boolean t = fsm.editarClassificacaoAluno(id, classificacao);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean editarAcessoAluno(Long id, String acesso) {
        boolean t = fsm.editarAcessoAluno(id, acesso);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean verificaIdAluno(Long id) {
        return fsm.verificaIdAluno(id);
    }

    public boolean inserirDocente(String nome, String email) {
        boolean t = fsm.inserirDocente(nome, email);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean verificaEmailDocente(String emailDocente) {
        return fsm.verificaEmailDocente(emailDocente);
    }

    public boolean editarEmailDocente(String emailantigo, String emailnovo) {
        boolean t = fsm.editarEmailDocente(emailantigo, emailnovo);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean eliminarDocente(String email) {
        boolean t = fsm.eliminarDocente(email);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean insereEstagio(String codProposta, String ramo, String titulo, String instituicao, long nrAluno) {
        boolean t = fsm.insereEstagio(codProposta, ramo, titulo, instituicao, nrAluno);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean insereProjeto(String codProposta, String ramo, String titulo, String emailDocente, long nrAluno) {
        boolean t = fsm.insereProjeto(codProposta, ramo, titulo, emailDocente, nrAluno);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean insereAutoproposto(String codProposta, String titulo, long nrAluno) {
        boolean t = fsm.insereAutoproposto(codProposta, titulo, nrAluno);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean eliminaProposta(String codProposta) {
        boolean t = fsm.eliminaProposta(codProposta);
        pcs.firePropertyChange(null, null, fsm.getState());
        return t;
    }

    public boolean consultarPropostas(String codProposta) {
        return fsm.consultarPropostas(codProposta);
    }

    public void estadoSeguinte(IGestaoEstagioState newState) {
        fsm.estadoSeguinte(newState);
    }

    public void regressaFaseAnterior() {
        fsm.regressaFaseAnterior();
        pcs.firePropertyChange(null, null, null);
    }

    public boolean fecharFaseCandidatura() {
        Boolean b = fsm.fecharFaseCandidatura();
        pcs.firePropertyChange(null, null, null);
        return b;
    }

    public boolean fecharFaseAtribuiPropostas() {
        Boolean b = fsm.fecharFaseAtribuiPropostas();
        pcs.firePropertyChange(null, null, null);
        return b;
    }

    public boolean fecharFaseOrientador() {
        Boolean b = fsm.fecharFaseOrientador();
        pcs.firePropertyChange(null, null, null);
        return b;
    }

    public void avancarFase() {
        fsm.avancarFase();
        pcs.firePropertyChange(null, null, fsm.getState());
    }

    public Docente getDocente(String email) {
        return fsm.getDocente(email);
    }

    public boolean consulta(String id) {
        return fsm.consulta(id);
    }

    public List<Candidaturas> consultaCandidaturas() {
        return fsm.consultaCandidaturas();
    }

    public boolean insereCandidatura(Long id, String propostas) {
        Boolean f = fsm.insereCandidatura(id, propostas);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean consultaAutoProposto(String cod) {
        return fsm.consultaAutoProposto(cod);
    }

    public boolean consultaEstagio(String cod) {
        return fsm.consultaEstagio(cod);
    }

    public boolean consultaProjeto(String cod) {
        return fsm.consultaProjeto(cod);
    }

    public List<Docente> listaDocentes() {
        return fsm.listaDocentes();
    }

    public Boolean getAcesso(String id) {
        return fsm.getAcesso(id);
    }

    public boolean importaAlunos(String filename) throws Exception {
        Boolean f = fsm.importaAlunos(filename);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean importaDocentes(String filename) throws Exception {
        Boolean f = fsm.importaDocentes(filename);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean importaPropostas(String filename) throws Exception {
        Boolean f = fsm.importaPropostas(filename);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean importaCandidaturas(String filename) throws Exception {
        Boolean f = fsm.importaCandidaturas(filename);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public List<Aluno> listaAutoproposta() {
        return fsm.listaAutoproposta();
    }

    public List<Aluno> listaComCandidaturas() {
        return fsm.listaComCandidaturas();
    }

    public List<Candidaturas> listaCandidaturasLock() {
        return fsm.listaCandidaturasLock();
    }

    public List<Aluno> listaSemCandidaturas() {
        return fsm.listaSemCandidaturas();
    }

    public List<Propostas> listaAutopropostas() {
        return fsm.listaAutopropostas();
    }

    public List<Propostas> listaProjetos() {
        return fsm.listaProjetos();
    }

    public List<Propostas> listaPropComCand() {
        return fsm.listaPropComCand();
    }

    public List<Propostas> listaPropSemCand() {
        return fsm.listaPropSemCand();
    }

    public List<Propostas> listaProjetoAtribuido() {
        return fsm.listaProjetoAtribuido();
    }

    public boolean atribuiAutoProposta() {
        Boolean f = fsm.atribuiAutoProposta();
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean atribuiPropostaManual(long nrAluno, String codProposta) {
        Boolean f = fsm.atribuiPropostaManual(nrAluno, codProposta);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean atribuiPropostaDisp() {
        Boolean f = fsm.atribuiPropostaDisp();
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public List<Aluno> listaAlunosComAutoProp() {
        return fsm.listaAlunosComAutoProp();
    }

    public List<Aluno> listaAlunosComCand() {
        return fsm.listaAlunosComCand();
    }

    public Map<Aluno, Integer> listaPropostaAtribuida() {
        return fsm.listaPropostaAtribuida();
    }

    public List<Aluno> listaAlunosComProposta() {
        return fsm.listaAlunosComProposta();
    }

    public List<Propostas> propostasDisponiveis() {
        return fsm.propostasDisponiveis();
    }

    public List<Propostas> propostasAtribuidas() {
        return fsm.propostasAtribuidas();
    }

    public Map<Docente, List<String>> orientacoesDocente() {
        return fsm.orientacoesDocente();
    }

    public boolean removePropostaManual(long nrAluno, String codProposta) {
        Boolean f = fsm.removePropostaManual(nrAluno, codProposta);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public List<String> getOrientadores() {
        return fsm.getOrientadores();
    }

    public double getMediaOrientacao() {
        return fsm.getMediaOrientacao();
    }

    public int getMinimoOrientacao() {
        return fsm.getMinimoOrientacao();
    }

    public int getOrientacaoDocente(String doc) {
        return fsm.getOrientacaoDocente(doc);
    }

    public int getMaximaOrientacao() {
        return fsm.getMaximaOrientacao();
    }

    public List<Aluno> listaAlunosSemAtribuicao() {
        return fsm.listaAlunosSemAtribuicao();
    }

    public List<Propostas> listaPropostasDisp() {
        return fsm.listaPropostasDisp();
    }

    public List<Propostas> listaPropostasAtribuidas() {
        return fsm.listaPropostasAtribuidas();
    }

    public boolean atribuiProponentesProjeto() {
        Boolean f = fsm.atribuiProponentesProjeto();
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean atribuiProponenteManual(String cod,String email) {
        Boolean f = fsm.atribuiProponenteManual(cod,email);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean editarNomeDocente(String email, String nome) {
        Boolean f = fsm.editarNomeDocente(email, nome);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean removeProponenteManual(String cod, String email) {
        Boolean f =  fsm.removeProponenteManual(cod,email);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public List<Aluno> listaAlunoComOrientador() {
        return fsm.listaAlunoComOrientador();
    }

    public List<Aluno> listaAlunoSemOrientador() {
        return fsm.listaAlunoSemOrientador();
    }

    public List<String> contaOrientacoes() {
        return fsm.contaOrientacoes();
    }

    public Map<Docente, List<String>> listanOrientacoes() {
        return fsm.listanOrientacoes();
    }

    public List<Aluno> listaAlunosComCandSemProp() {
        return fsm.listaAlunosComCandSemProp();
    }

    public void load(File file) throws Exception {
        System.out.println("das load?");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            fsm = (GestaoEstagioContext) ois.readObject();
        } catch (Exception e) {
            throw new Exception("Erro ao carregar a informação atual no sistema");
        }
        pcs.firePropertyChange(null, null, null);
    }

    public void save(File file) throws Exception {
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(file))) {
            ois.writeObject(fsm);
        } catch (Exception e) {
            throw new Exception("Erro ao guardar a informação atual no sistema");
        }

    }

    public boolean alteraProponente(String cod, String email) {
        boolean f = fsm.alteraProponente(cod, email);
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public List<Docente> consultaProponente() {
        return fsm.consultaProponente();
    }

    public void exportaCsvAlunos(String filename) throws Exception {
        fsm.exportaCsvAlunos(filename);
        pcs.firePropertyChange(null, null, null);
    }

    public void exportaCsvDocentes(String filename) throws Exception {
        fsm.exportaCsvDocentes(filename);
        pcs.firePropertyChange(null, null, null);
    }

    public void exportaCsvPropostas(String filename) throws Exception {
        fsm.exportaCsvPropostas(filename);
        pcs.firePropertyChange(null, null, null);
    }

    public void exportaCsvCandidaturas(String filename) throws Exception {
        fsm.exportaCsvCandidaturas(filename);
    }

    public void exportaCsvFase3(String filename) throws Exception {
        fsm.exportaCsvPropostas(filename);
        fsm.exportaCsvFase3(filename);
    }

    public void exportaCsvFase4(String filename) throws Exception {
        fsm.exportaCsvFase4(filename);
    }

    public List<Aluno> listaAlunos() {
        return fsm.listaAlunos();
    }

    public List<Propostas> listaPropostas() {
        return fsm.listaPropostas();
    }

    public void consultaOrientadorAtribuido() {
        fsm.consultaOrientadorAtribuido();
    }

    public boolean undo() {
        Boolean f = fsm.undo();;
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public boolean redo() {
        Boolean f = fsm.redo();;
        pcs.firePropertyChange(null, null, null);
        return f;
    }

    public Aluno getAluno(String id) {
        return fsm.getAluno(id);
    }

    public Propostas getProposta(String text) {
        return fsm.getProposta(text);
    }
}
