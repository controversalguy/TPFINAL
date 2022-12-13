package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.GestaoEstagioData;
import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.util.List;
import java.util.Map;

abstract class GestaoEstagioStateAdapter implements IGestaoEstagioState {
    GestaoEstagioContext context;
    GestaoEstagioData data;

    public GestaoEstagioStateAdapter(GestaoEstagioContext context, GestaoEstagioData data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void start() {
    }

    @Override
    public boolean importaPropostas(String filename) throws Exception {
        return false;
    }

    @Override
    public boolean importaDocentes(String filename) throws Exception {
        return false;
    }

    @Override
    public boolean importaAlunos(String filename) throws Exception {
        return false;
    }

    @Override
    public boolean importaCandidaturas(String filename) throws Exception {
        return false;
    }
    @Override
    public boolean insereCandidatura(Long id, String propostas){
        return false;
    }

    @Override
    public List<Candidaturas> consultaCandidaturas(){return null;}

    @Override
    public List<Aluno> listaAutoproposta() {
        return null;
    }

    @Override
    public List<Aluno> listaCandidaturas() {

        return null;
    }

    @Override
    public List<Aluno> listaSemCandidaturas() {
        return null;
    }

    @Override
    public List<Propostas> listaAutopropostas() {
        return null;
    }
    @Override
    public List<Propostas> listaProjetoAtribuido(){return null;}

    @Override
    public List<Propostas> listaProjetos() {
        return null;
    }

    @Override
    public List<Propostas> listaPropComCand() {
        return null;
    }

    @Override
    public List<Propostas> listaPropSemCand() {
        return null;
    }

    //Alunos

    @Override
    public boolean verificaIdAluno(Long id) {
        return false;
    }

    @Override
    public boolean inserirAluno(Long id, String nome, String email, String curso, String ramo, Double classificacao, String podeEstagio) {
        return false;
    }

    @Override
    public boolean consultarPropostas(String codProposta) {
        return false;
    }

  /*  @Override
    public boolean editarAlunoCurso(Long id, String curso) {
        return false;
    }

    @Override
    public boolean editarAlunoRamo(Long id, String ramo) {
        return false;
    }*/

    @Override
    public boolean editarAlunoClassificacao(Long id, double classificacao) {
        return false;
    }

   /* @Override
    public boolean editarAcessoEstagio(Long id, boolean acess) {
        return false;
    }*/

    @Override
    public boolean editarNomeAluno(Long id, String novoNome) {
        return false;
    }
/*
    @Override
    public boolean consultarAluno(long id) {
        return false;
    }
*/
    //Docentes

    @Override
    public boolean verificaEmailDocente(String emailDocente) {
        return false;
    }
    @Override
    public boolean editarNomeDocente(String email, String nome){return false;}

    @Override
    public boolean inserirDocente(String nome, String email) {
        return false;
    }

   /* @Override
    public boolean editarDocenteNome(String email, String novoNome){return false;}*/

    @Override
    public boolean editarEmailAluno(Long Identifier, String novoEmail) {
        return false;
    }


    @Override
    public boolean fecharFaseOrientador() {
        return false;
    }

    //Propostas
    @Override
    public boolean insereEstagio(String codProposta, String ramo, String titulo, String instituicao, long nrAluno) {
        return false;
    }

    @Override
    public boolean insereProjeto(String codProposta, String ramo, String titulo, String emailDocente, long nrAluno) {
        return false;
    }

    @Override
    public boolean editarNome(String id, String novoNome) {
        return false;
    }
    @Override
    public boolean editarEmailDocente(String emailantigo,String emailnovo){return false;}

    @Override
    public boolean consulta(String id) {
        return false;
    }

    @Override
    public boolean insereAutoproposto(String codProposta, String titulo, long nrAluno) {
        return false;
    }

    void estadoSeguinte(GestaoEstagioState newState) {
        context.estadoSeguinte(newState.createState(context, data));
    }

    @Override
    public boolean recebeModo(int op) {
        return false;
    }

    @Override
    public boolean editarRamoAluno(Long id, String ramo){return false;}
    @Override
    public boolean editarCursoAluno(Long id, String curso){return false;}
    @Override
    public boolean editarClassificacaoAluno(Long id, Double classificacao){return false;}
    @Override
    public boolean editarAcessoAluno(Long id, String acesso){return false;}
    @Override
    public boolean editarProposta(String cod, String dados, int op){return false;}

    @Override
    public boolean eliminar(String identifier) {
        return false;
    }

    @Override
    public Aluno getAluno(String id){return null;}
    @Override
    public List<Candidaturas> listaCandidaturasLock(){return null;}

    @Override
    public void regressarFaseAnterior() {
    }

    @Override
    public Propostas getProposta(String text) {
        return null;
    }

    @Override
    public boolean fecharFaseCandidatura() {
        return false;
    }

    @Override
    public boolean fecharFaseAtribuiPropostas() {
        return false;
    }

    @Override
    public void avancarFase() {
    }

    @Override
    public boolean atribuiAutoProposta() {

        return false;
    }

    @Override
    public boolean atribuiPropostaManual(long nrAluno, String codProposta) {
        return false;
    }

    @Override
    public List<Aluno> listaAlunosComAutoProp() {
        return null;
    }

    @Override
    public List<Aluno> listaAlunosComCand() {
        return null;
    }

    @Override
    public boolean atribuiPropostaDisp() {
        return false;
    }

    @Override
    public Map<Aluno, Integer> listaPropostaAtribuida() {
        return null;
    }

    @Override
    public boolean removePropostaManual(long nrAluno,String codProposta) {
        return false;
    }

    @Override
    public boolean atribuiProponenteManual(String cod, String email){return false;}

    @Override
    public boolean removeProponenteManual(String cod, String email){
        return false;
    }
    @Override
    public boolean alteraProponente(String cod, String email){return false;}

    @Override
    public List<Aluno> listaAlunosSemAtribuicao() {
        return null;
    }

    @Override
    public List<Propostas> listaPropostasDisp() {
    return null;
    }

    @Override
    public List<Propostas> listaPropostasAtribuidas() {
        return null;
    }

    @Override
    public boolean atribuiProponentesProjeto() {
        return false;
    }

    @Override
    public List<Aluno> listaAlunoComOrientador() {
        return null;
    }

    @Override
    public List<Aluno> listaAlunoSemOrientador() {
        return null;
    }
    @Override
    public List<Docente> consultaProponente(){return null;}

    @Override
    public List<String> contaOrientacoes() {
        return null;
    }

    @Override
    public Map<Docente, List<String>> listanOrientacoes() {
        return null;
    }

    @Override
    public List<Aluno> listaAlunosComCandSemProp() {
        return null;
    }

    @Override
    public void exportaCsvAlunos(String filename) throws Exception {

    }

    @Override
    public void exportaCsvDocentes(String filename) throws Exception {

    }

    @Override
    public void exportaCsvPropostas(String filename) throws Exception {

    }

    @Override
    public void exportaCsvCandidaturas(String filename) throws Exception {

    }

    @Override
    public void exportaCsvFase3(String filename) throws Exception {
    }

    @Override
    public void exportaCsvFase4(String filename) throws Exception {
    }

    @Override
    public boolean consultaEstagio(String cod) {
        return false;
    }

    @Override
    public boolean consultaProjeto(String cod) {
        return false;
    }

    @Override
    public boolean consultaAutoProposto(String cod) {
        return false;
    }


    @Override
    public void listaOrientadorAtribuido() {

    }

    @Override
    public List<Propostas> listaPropostas() {
        return null;
    }
    @Override
    public boolean getAcesso(String id){return false;}

    @Override
    public List<Aluno> listaAlunos() {
        return null;
    }
    @Override
    public Docente getDocente(String email){
        return null;
    }

    @Override
    public List<Docente> listaDocentes() {
        return null;
    }
    @Override
    public List<Aluno> listaAlunosComProposta() {
        return null;
    }
    @Override
    public List<Propostas> propostasDisponiveis() {
        return null;
    }
    @Override
    public List<Propostas> propostasAtribuidas() {
        return null;
    }
    @Override
    public List<String> getOrientadores(){return null;}
    @Override
    public int getOrientacaoDocente(String doc){return 0;}

    @Override
    public double getMediaOrientacao(){return 0;}
    @Override
    public int getMinimoOrientacao(){return 0;}
    @Override
    public int getMaximaOrientacao(){return 0;}

    @Override
    public Map<Docente, List<String>> orientacoesDocente(){
        return null;
    }
}
