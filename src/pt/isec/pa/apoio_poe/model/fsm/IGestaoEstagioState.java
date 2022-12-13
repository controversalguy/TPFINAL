package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;
import pt.isec.pa.apoio_poe.model.data.candidaturadata.Candidaturas;
import pt.isec.pa.apoio_poe.model.data.docentedata.Docente;
import pt.isec.pa.apoio_poe.model.data.projetodata.Propostas;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGestaoEstagioState extends Serializable {
    boolean editarNomeAluno(Long id, String novoNome);

    boolean editarEmailAluno(Long Identifier, String novoEmail);

    boolean editarRamoAluno(Long id, String ramo);

    boolean editarCursoAluno(Long id, String curso);

    boolean editarClassificacaoAluno(Long id, Double classificacao);

    boolean editarAcessoAluno(Long id, String acesso);

    boolean editarProposta(String cod, String dados, int op);

    boolean eliminar(String identifier);

    boolean editarEmailDocente(String emailantigo,String emailnovo);

    boolean consulta(String id);

    boolean editarNome(String id, String novoNome);

    // Config
    boolean recebeModo(int op);

    // fechar ou recuar
    boolean fecharFaseOrientador();

    Aluno getAluno(String id);

    Propostas getProposta(String text);

    List<Candidaturas> listaCandidaturasLock();

    void regressarFaseAnterior();

    boolean fecharFaseCandidatura();

    boolean fecharFaseAtribuiPropostas();

    void avancarFase();

    // Alunos

    boolean inserirAluno(Long id, String nome, String email, String curso, String ramo, Double classificacao, String podeEstagio);


    List<Aluno> listaCandidaturas();

    List<Aluno> listaSemCandidaturas();

    List<Propostas> listaAutopropostas();

    List<Propostas> listaProjetoAtribuido();

    List<Propostas> listaProjetos();

    List<Propostas> listaPropComCand();

    List<Propostas> listaPropSemCand();

    //boolean eliminarAluno(Long id);
    boolean verificaIdAluno(Long id);

    boolean consultarPropostas(String codProposta);

    //boolean editarAlunoEmail(Long id, String novoEmail);
  /*  boolean editarAlunoNome(Long id,String novoNome);
    boolean editarAlunoCurso(Long id, String curso);
    boolean editarAlunoRamo(Long id, String ramo);*/
    boolean editarAlunoClassificacao(Long id, double classificacao);



    // Docentes
    boolean verificaEmailDocente(String emailDocente);

    boolean editarNomeDocente(String email, String nome);

    boolean inserirDocente(String nome, String email);
    /* boolean editarDocenteNome(String email, String novoNome);*/
    //boolean editarDocenteEmail(String email, String novoEmail);
    //boolean eliminarDocente(String email);

    //Propostas
    boolean insereEstagio (String codProposta, String ramo, String titulo, String instituicao, long nrAluno);

    boolean insereProjeto(String codProposta, String ramo, String titulo, String emailDocente, long nrAluno);

    boolean insereAutoproposto(String codProposta, String titulo, long nrAluno);

    //boolean eliminaProposta(String codProposta);

    //importar
    boolean importaPropostas(String filename) throws  Exception;

    boolean importaDocentes(String filename) throws  Exception;

    boolean importaAlunos(String filename) throws  Exception;

    boolean importaCandidaturas(String filename) throws Exception;

    boolean consultaEstagio(String cod);
    boolean consultaProjeto(String cod);
    boolean consultaAutoProposto(String cod);
    // Candidaturas

    boolean insereCandidatura(Long id, String propostas);

    List<Candidaturas> consultaCandidaturas();

    List<Aluno> listaAutoproposta();

    // Estágios
    /* boolean editarAcessoEstagio(Long id, boolean access);*/

    // AutoProposto
    boolean atribuiAutoProposta();
    boolean atribuiPropostaManual(long nrAluno, String codProposta);

    GestaoEstagioState getState();


    List<Aluno> listaAlunosComAutoProp();

    List<Aluno> listaAlunosComCand();

    boolean atribuiPropostaDisp();

    Map<Aluno, Integer> listaPropostaAtribuida();

    boolean removePropostaManual(long nrAluno,String codProposta);

    boolean atribuiProponenteManual(String cod, String email);

    boolean removeProponenteManual(String cod, String email);

    boolean alteraProponente(String cod, String email);

    List<Aluno> listaAlunosSemAtribuicao();

    List<Propostas> listaPropostasDisp();

    List<Propostas> listaPropostasAtribuidas();

    boolean atribuiProponentesProjeto();

    List<Aluno> listaAlunoComOrientador();

    List<Aluno> listaAlunoSemOrientador();

    List<Docente> consultaProponente();

    List<String> contaOrientacoes();

    Map<Docente, List<String>> listanOrientacoes();

    List<Aluno> listaAlunosComCandSemProp();

    void exportaCsvAlunos(String filename) throws Exception;

    void exportaCsvDocentes(String filename) throws Exception;

    void exportaCsvPropostas(String filename) throws Exception;

    void exportaCsvCandidaturas(String filename) throws Exception;

    void exportaCsvFase3(String filename) throws Exception;

    void exportaCsvFase4(String filename) throws Exception;

    void listaOrientadorAtribuido();

    List<Propostas> listaPropostas();

    boolean getAcesso(String id);

    List<Aluno> listaAlunos();

    Docente getDocente(String email);

    List<Docente> listaDocentes();

    List<Aluno> listaAlunosComProposta();

    List<Propostas> propostasDisponiveis();

    List<Propostas> propostasAtribuidas();

    List<String> getOrientadores();

    int getOrientacaoDocente(String doc);

    double getMediaOrientacao();

    int getMinimoOrientacao();

    int getMaximaOrientacao();

    Map<Docente, List<String>> orientacoesDocente();

    void start();
}
