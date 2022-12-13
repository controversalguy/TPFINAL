package pt.isec.pa.apoio_poe.ui.text;


import pt.isec.pa.apoio_poe.model.fsm.GestaoEstagioManager;
import pt.isec.pa.apoio_poe.utils.PAInput;
import java.util.Scanner;

public class GestaoEstagioUI {
    Scanner sc;
    GestaoEstagioManager fsm;
    boolean finish = false;

    public GestaoEstagioUI(GestaoEstagioManager fsm) {
        this.fsm = fsm;
        sc = new Scanner(System.in);
    }


    public void start() throws Exception {
        while (!finish) {
            switch (fsm.getState()) {
                case INICIO -> inicioUI();
                case INICIA_CONFIG -> iniciaConfigUI();
                case CANDIDATURA -> candidaturaUI();
                case GERE_ALUNOS -> gereAlunosUI();
                case GERE_DOCENTES -> gereDocentesUI();
                case GERE_PROPOSTAS -> gerePropostaUI();
                case EXPORTA_DADOS -> exportaDadosIniciaConfigUI();
                case IMPORTA_DADOS -> importaDadosUI();
                case ATRIBUICAO_PROPOSTA -> AtribuicaoPropostaUI();
                case ATRIBUICAO_ORIENTADOR -> atribuicaoOrientadorUI();
                case INICIA_CONFIG_LOCK -> iniciaConfigUILock();
                case CANDIDATURA_LOCK -> candidaturaUILock();
                case ATRIBUICAO_PROPOSTA_LOCK -> atribuicaoPropostaUILock();
                case CONSULTA -> consultaUI();
            }
        }
    }
    private void inicioUI() {
        int op = PAInput.chooseOption("Inicio", "Começar","Sair");
        switch (op) {
            case 1 -> fsm.start();
            case 2 -> finish = true;
        }
    }
    private void atribuicaoPropostaUILock() {
        int op = PAInput.chooseOption("AtribuicaoPropostaLock", "Consulta Propostas Atribuidas", "Avancar", "Regressa Fase Anterior", "Save", "Quit");
        switch (op) {
            case 1 -> System.out.println(fsm.listaPropostasAtribuidas());
            case 2 -> fsm.avancarFase();
            case 3 -> fsm.regressaFaseAnterior();
            case 4 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 5 -> finish = true;
        }
    }

    private void candidaturaUILock() {
        int op = PAInput.chooseOption("CandidaturaLock", "Consulta Candidaturas", "Avancar Fase", "Regressa Fase", "Save", "Quit");
        switch (op) {
            case 1 -> System.out.println(fsm.listaComCandidaturas());
            case 2 -> fsm.avancarFase();
            case 3 -> fsm.regressaFaseAnterior();
            case 4 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 5 -> finish = true;
        }
    }

    private void iniciaConfigUI() {
        int op = PAInput.chooseOption("Config", "Gerir Alunos", "Gerir Docentes", "Gere Propostas", "Importar dados", "Exportar dados", "Fechar Fase", "Avançar Fase", "Save", "Load", "Quit");
        fsm.recebeModo(op);
        if (op == 8) {
            System.out.println("Deseja gravar em que nome?");
            /*try {
                fsm.save(sc.nextLine());
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return;
            }*/
            System.out.println("Gravado com sucesso");
            finish = true;
        } else if (op == 9)
            /*try {
                fsm.load(sc.nextLine());
            } catch (Exception e) {*/
                System.err.println("e.getMessage()");
            //}
        else if (op == 10) {
            finish = true;
        }

    }

    private void iniciaConfigUILock() {
        int op = PAInput.chooseOption("ConfigLock", "Consulta Alunos", "Consulta Docentes", "Consulta Propostas", "Avancar", "Save", "Quit");
        switch (op) {
            case 1 -> System.out.println(fsm.listaAlunos());
            case 2 -> System.out.println(fsm.listaDocentes());
            case 3 -> System.out.println(fsm.listaPropostas());
            case 4 -> fsm.avancarFase();
            case 5 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 6 -> finish = true;
        }
    }

    private void importaDadosUI() {
        int op = PAInput.chooseOption("Importar dados", "Alunos", "Docentes", "Propostas");
        switch (op) {
            case 1 -> {
                System.out.println("Escreva o nome do ficheiro que pretende importar");
                try {
                    fsm.importaAlunos(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }
                System.out.println("Alunos importados com sucesso");
            }
            case 2 -> {
                System.out.println("Escreva o nome do ficheiro que pretende importar");
                try {
                    fsm.importaDocentes(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }
                System.out.println("Docentes importados com sucesso");
            }
            case 3 -> {
                System.out.println("Escreva o nome do ficheiro que pretende importar");
                try {
                    fsm.importaPropostas(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }
                System.out.println("Propostas importadas com sucesso");
            }
        }
    }

    private void exportaDadosIniciaConfigUI() throws Exception {
        int op = PAInput.chooseOption("Exportar dados", "Alunos", "Docentes", "Propostas");
        switch (op) {
            case 1 -> {
                String filename = sc.nextLine();
                fsm.exportaCsvAlunos(filename);
            }
            case 2 -> {
                String filename = sc.nextLine();
                fsm.exportaCsvDocentes(filename);
            }
            case 3 -> {
                String filename = sc.nextLine();
                fsm.exportaCsvPropostas(filename);
            }
        }
    }

    private void gereEstagiosUI() {
        switch (PAInput.chooseOption("**** Estágio ****", "Inserir", "Editar", "Eliminar", "Consultar")) {
            case 1 -> {
                System.out.println("Digite codigo proposta:");
                String codigo = sc.next();
                System.out.println("Digite o rammo:");
                String ramo = sc.next();
                System.out.println("Digite o titulo:");
                sc.nextLine();
                String titulo = sc.nextLine();
                System.out.println("Digite a instituicao:");
                sc.nextLine();
                String inst = sc.nextLine();
                System.out.println("Digite nr de aluno:");
                long nraluno = sc.nextLong();
                if(!fsm.insereEstagio(codigo, ramo, titulo, inst, nraluno)) System.out.println("Estagio Invalido");
                else System.out.println("Estagio inserido com Sucesso");
            }
            case 2 -> {
                System.out.println("Digite o Codigo da proposta que quer editar");
                String cod = sc.next();
                switch (PAInput.chooseOption("**** Editar Estagio ****", "Ramo", "Titulo", "Instituicao", "Nr Aluno")){
                    case 1 -> {System.out.println("Edite o seu Ramo:");String ramo = sc.next();
                        if(!fsm.editarProposta(cod,ramo,1))
                            System.out.println("Codigo ou Ramo Invalidos");}
                    case 2 -> {System.out.println("Edite Titulo:"); sc.nextLine();String titulo = sc.nextLine();
                        if(!fsm.editarProposta(cod,titulo,2))
                            System.out.println("Codigo ou titulo Invalidos");}
                    case 3 -> {System.out.println("Edite Instituicao:"); sc.nextLine();String instituicao = sc.nextLine();
                        if(!fsm.editarProposta(cod,instituicao,3))
                            System.out.println("Codigo ou instituicao Invalidos");}
                    case 4 -> {System.out.println("Edite o seu Nr Aluno:");String id = sc.next();
                        if(!fsm.editarProposta(cod,id,4))
                            System.out.println("Codigo ou Nr Aluno Invalidos");}
                }
            }
            case 3 -> {
                System.out.println("Digite Codigo Proposta que pretende eliminar");
                String cod = sc.next();
                if(!fsm.eliminaProposta(cod)) System.out.println("Codigo Invalido");
                else System.out.println("Estagio eliminado com Sucesso");}
            case 4 -> {
                System.out.println("Digite o código do Estágio que pretende consultar");
                String cod = sc.next();
                if (fsm.consultaEstagio(cod))
                    System.out.println("A proposta desejada:");
                else
                    System.out.println("A proposta que procura não existe no sistema");
            }
        }
    }

    private void gereProjetosUI() {
        switch (PAInput.chooseOption("**** Projeto ****", "Inserir", "Editar", "Eliminar", "Consultar")) {
            case 1 -> {
                System.out.println("Digite codigo proposta:");
                String codigo = sc.next();
                System.out.println("Digite o ramo:");
                String ramo = sc.next();
                System.out.println("Digite o titulo:");
                sc.nextLine();
                String titulo = sc.nextLine();
                System.out.println("Digite email do docente:");
                String emailDocente = sc.next();
                System.out.println("Digite nr de aluno:");
                long nraluno = sc.nextLong();
                if(!fsm.insereProjeto(codigo, ramo, titulo, emailDocente, nraluno)) System.out.println("Projeto Invalido");
                else System.out.println("Projeto inserido com Sucesso");
            }
            case 2 -> {
                System.out.println("Digite o Codigo da proposta que quer editar");
                String cod = sc.next();
                switch (PAInput.chooseOption("**** Editar Projeto ****", "Ramo", "Titulo", "Email Docente", "Nr Aluno")){
                    case 1 -> {System.out.println("Edite o seu Ramo:");String ramo = sc.next();
                        if(!fsm.editarProposta(cod,ramo,1))
                            System.out.println("Codigo ou Ramo Invalidos");}
                    case 2 -> {System.out.println("Edite Titulo:"); sc.nextLine();String titulo = sc.nextLine();
                        if(!fsm.editarProposta(cod,titulo,2))
                            System.out.println("Codigo ou titulo Invalidos");}
                    case 3 -> {System.out.println("Edite Email:"); String email = sc.next();
                        if(!fsm.editarProposta(cod,email,5))
                            System.out.println("Codigo ou email Invalidos");}
                    case 4 -> {System.out.println("Edite o seu Nr Aluno:");String id = sc.next();
                        if(!fsm.editarProposta(cod,id,4))
                            System.out.println("Codigo ou Nr Aluno Invalidos");}
                }
            }
            case 3 -> {System.out.println("Digite Codigo Proposta que pretende eliminar");
                String cod = sc.next();
                if(!fsm.eliminaProposta(cod)) System.out.println("Codigo Invalido");
                else System.out.println("Projeto eliminado com Sucesso");}

            case 4 -> {//consultar
                System.out.println("Digite o código do Projetos que pretende consultar");
                String cod = sc.next();
                if (fsm.consultaProjeto(cod))
                    System.out.println("O Projeto desejada:");
                else
                    System.out.println("O Projeto que procura não existe no sistema");
            }
        }
    }

    private void gereAutoPropostoUI() {
        //TODO Verificacao codigo proposto e nr de aluno
        switch (PAInput.chooseOption("**** Estágio/Projeto Autoproposto ****", "Inserir", "Editar", "Eliminar", "Consultar")) {
            case 1 -> {
                System.out.println("Digite codigo proposta:");
                String codigo = sc.next();
                System.out.println("Digite o titulo:");
                sc.nextLine();
                String titulo = sc.nextLine();
                System.out.println("Digite nr de aluno:");
                long nraluno = sc.nextLong();
                if(!fsm.insereAutoproposto(codigo, titulo, nraluno)) System.out.println("AutoProposto Invalido");
                else System.out.println("AutoProposta inserida com Sucesso");
            }
            case 2 -> {
                System.out.println("Digite o Codigo da proposta que quer editar");
                String cod = sc.next();
                switch (PAInput.chooseOption("**** Editar AutoProposto ****","Titulo","Nr Aluno")){
                    case 1 -> {System.out.println("Edite Titulo:"); sc.nextLine();String titulo = sc.nextLine();
                        if(!fsm.editarProposta(cod,titulo,2))
                            System.out.println("Codigo ou titulo Invalidos");}
                    case 2 -> {System.out.println("Edite o seu Nr Aluno:");String id = sc.next();
                        if(!fsm.editarProposta(cod,id,4))
                            System.out.println("Codigo ou Nr Aluno Invalidos");}
                }
            }
            case 3 -> {System.out.println("Digite Codigo Proposta que pretende eliminar");
                String cod = sc.next();
                if(!fsm.eliminaProposta(cod)) System.out.println("Codigo Invalido");
                else System.out.println("AutoProposta eliminada com Sucesso");}
            case 4 -> {//consultar
                System.out.println("Digite o código da AutoProposta que pretende consultar");
                String cod = sc.next();
                if (!fsm.consultaAutoProposto(cod))
                    System.out.println("Não existe nenhuma AutoProposta com esse código");
            }
        }
    }

    private void gereAlunosUI() {
        switch (PAInput.chooseOption("**** Aluno ****", "Inserir", "Editar", "Eliminar", "Consultar")) {
            case 1 -> {
                System.out.println("Digite o ID:");
                Long id = sc.nextLong();
                System.out.println("Digite o seu nome:");
                sc.nextLine();
                String nome = sc.nextLine();
                System.out.println("Digite o seu email:");
                String email = sc.next();
                System.out.println("Digite o seu curso:");
                String curso = sc.next();
                System.out.println("Digite o seu ramo:");
                String ramo = sc.next();
                System.out.println("Digite a sua classificação final:");
                //double classificacao = sc.nextDouble();
                Double classificacao=Double.parseDouble(sc.next());
                System.out.println("Digite Acesso Estagio (true/false):");
                String acesso = sc.next();
                if (!fsm.inserirAluno(id, nome, email, curso, ramo, classificacao, acesso)) System.out.println("Aluno Invalido");
                else System.out.println("Aluno inserido com Sucesso");
            }
            case 2 -> {
                System.out.println("Digite o ID do Aluno que quer editar");
                Long id = sc.nextLong();
                switch (PAInput.chooseOption("**** Editar Aluno ****", "Nome", "email", "Curso", "Ramo", "Classificacao","Acesso Estagio")) {
                    case 1 -> {System.out.println("Edite nome:");sc.nextLine();String nome = sc.nextLine();
                        if(!fsm.editarNomeAluno(id,nome))
                            System.out.println("Id ou email Invalidos");}
                    case 2 -> {System.out.println("Edite o seu email:");String email = sc.next();
                        if(!fsm.editarEmailAluno(id,email))
                            System.out.println("Id ou email Invalidos");}
                    case 3 -> {System.out.println("Edite o seu curso:");String curso = sc.next();
                        if(!fsm.editarCursoAluno(id,curso))
                            System.out.println("Id ou Curso Invalidos");}
                    case 4 -> {System.out.println("Edite o seu ramo:");String ramo = sc.next();
                        if(!fsm.editarRamoAluno(id,ramo))
                            System.out.println("Id ou ramo Invalidos");}
                    case 5 -> {System.out.println("Edite a sua classificação final:");Double classificacao = sc.nextDouble();
                        if(!fsm.editarClassificacaoAluno(id,classificacao))
                            System.out.println("Id ou Classificacao Invalidos");}
                    case 6 -> {System.out.println("Edite acesso a Estagio (true/false):");String acesso = sc.next();
                        if(!fsm.editarAcessoAluno(id,acesso))
                            System.out.println("Id ou Acesso Invalidos");}}
            }
            case 3 -> {System.out.println("Digite o id do Aluno que pretende eliminar");
                long id = sc.nextLong();
                        if(!fsm.eliminarAluno(id)) System.out.println("ID aluno Invalido");
                        else System.out.println("Aluno eliminado com Sucesso");}
            case 4 -> System.out.println(fsm.listaAlunos());
        }
    }

    private void gereDocentesUI() {
        switch (PAInput.chooseOption("**** Docente ****", "Inserir", "Editar", "Eliminar", "Consultar")) {
            case 1 -> {
                System.out.println("Digite o seu nome:");sc.nextLine();String nome = sc.nextLine();
                System.out.println("Digite o seu email:");String email = sc.next();
                if (!fsm.inserirDocente(nome, email)) System.out.println("Docente Invalido");
                else System.out.println("Docente inserido com Sucesso");
            }
            case 2 -> {
                    System.out.println("Digite o seu email:");
                    String emailvelho = sc.next();
                    System.out.println("Edite o seu email:");
                    String emailnovo = sc.next();
                    if (!fsm.editarEmailDocente(emailvelho, emailnovo))
                        System.out.println("Email Invalido");

            }
            case 3 -> {
                System.out.println("Digite o email do Docente que pretende eliminar");
                String email = sc.next();
                if(!fsm.eliminarDocente(email)) System.out.println("Email Invalido");
                else System.out.println("Docente eliminado com Sucesso");}
            case 4 -> System.out.println(fsm.listaDocentes());
        }
    }

    private void gerePropostaUI() {
        switch (PAInput.chooseOption("**** Tipo de Proposta ****", "Estágio", "Projeto", "Estágio/Projeto Autoproposto","Consultar Propostas","Save")) {
            case 1 -> gereEstagiosUI();
            case 2 -> gereProjetosUI();
            case 3 -> gereAutoPropostoUI();
            case 4 -> System.out.println(fsm.listaPropostas());
            case 5 -> {
                System.out.println("Deseja gravar em que nome?");
               /* try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }

        }
    }

    private void candidaturaUI() throws Exception {
        int op = PAInput.chooseOption("Candidaturas", "Gere Candidatura", "Obtenção lista de ALunos", "Obtenção lista de Propostas",
                "Importa Candidaturas", "Exportar Candidaturas", "Fechar Fase", "Regressa Fase Anterior", "Avançar Fase", "Save", "Quit");
        switch (op) {
            case 1 -> {
                op = PAInput.chooseOption("Comandos Candidatura", "Inserir", "Consultar");
                switch (op) {
                    case 1 -> {
                        System.out.println("Digite nr de Aluno:");
                        Long id = sc.nextLong();
                        System.out.println("Digite Propostas");
                        sc.nextLine();
                        String propostas = sc.nextLine();
                        if(!fsm.insereCandidatura(id,propostas)) System.out.println("Nr aluno ou proposta inválido");
                        else System.out.println("Candidatura inserida com Sucesso");
                    }
                    case 2 -> System.out.println(fsm.consultaCandidaturas());
                }
            }
            case 2 -> {
                op = PAInput.chooseOption("Lista de ALunos: ", "Com autoproposta", "Com Candidatura Registada", "Sem Candidatura Registada");
                switch (op) {
                    case 1 -> System.out.println(fsm.listaAutoproposta());
                    case 2 -> System.out.println(fsm.listaComCandidaturas());
                    case 3 -> System.out.println(fsm.listaSemCandidaturas());
                }
            }
            case 3 -> {
                op = PAInput.chooseOption("Lista de Propostas: ", "Autopropostas de alunos", "Propostas de docentes",
                        "Propostas com candidaturas", "Propostas sem candidaturas");
                switch (op) {
                    case 1 -> System.out.println(fsm.listaAutopropostas());
                    case 2 -> System.out.println(fsm.listaProjetos());
                    case 3 -> System.out.println(fsm.listaPropComCand());
                    case 4 -> System.out.println(fsm.listaPropSemCand());
                }
            }
            case 4 -> {
                System.out.println("Escreva o nome do ficheiro que pretende importar");
                try {
                    fsm.importaCandidaturas(sc.nextLine());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("Candidaturas importadas com sucesso");
            }
            case 5 -> {
                String filename = sc.nextLine();
                fsm.exportaCsvCandidaturas(filename);
            }
            case 6 -> {
                if (!fsm.fecharFaseCandidatura()) {
                    System.err.println("Não pode fechar a fase atual devido à anterior não estar fechada");
                } else System.out.println("Fase Fechada com Sucesso");
            }
            case 7 -> fsm.regressaFaseAnterior();
            case 8 -> fsm.avancarFase();
            case 9 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 10 -> finish = true;
        }
    }

    private void AtribuicaoPropostaUI() throws Exception {
        int op = PAInput.chooseOption("Atribuição de Propostas", "Gere Atribuição de Propostas", "Obtenção lista de ALunos", "Obtenção lista de Propostas", "Exportar dados", "Save", "Fechar Fase", "Regressa Fase Anterior", "Avançar Fase", "Quit");
        switch (op) {
            case 1 -> {
                op = PAInput.chooseOption("Comandos Atribuicao Proposta", "Atribuição automática das autopropostas ou propostas de docentes", "Atribuição automática de uma proposta disponível aos alunos ainda sem atribuições\n" +
                        "definidas", "Atribuição manual de propostas disponíveis aos alunos sem atribuição ainda definida", "Remove Proposta Atribuida manualmente", "Undo", "Redo");
                switch (op) {
                    case 1 -> fsm.atribuiAutoProposta();
                    case 2 -> fsm.atribuiPropostaDisp();
                    case 3 -> {
                        System.out.println("Digite o nr do aluno que pretende Atribuir Proposta");
                        long nrAluno = sc.nextLong();
                        System.out.println("Digite Codigo Proposta a Atribuir");
                        String codigo = sc.next();
                        if (!fsm.atribuiPropostaManual(nrAluno, codigo))
                            System.out.println("Numero de aluno ou codigo da proposta é invalido");
                    }
                    case 4 -> {
                        System.out.println("Digite o nr do aluno que pretende remover Proposta");
                        long nrAluno = sc.nextLong();
                        System.out.println("Digite o código da Proposta que pretende remover");
                        String codProposta = sc.next();
                        if (!fsm.removePropostaManual(nrAluno, codProposta))
                            System.out.println("Numero de aluno ou codigo da proposta é invalido");
                    }
                    case 5 -> fsm.undo();
                    case 6 -> fsm.redo();
                }
            }
            case 2 -> {
                op = PAInput.chooseOption("Lista de ALunos", "Com Autoproposta Associada", "Com Candidatura Registada",
                        "Com Proposta Atribuida Por Ordem de Preferencia", "Sem Qualquer Proposta Atribuida");
                switch (op) {
                    case 1 -> System.out.println(fsm.listaAlunosComAutoProp());
                    case 2 -> System.out.println(fsm.listaAlunosComCand());
                    case 3 -> System.out.println(fsm.listaPropostaAtribuida());
                    case 4 -> System.out.println(fsm.listaAlunosSemAtribuicao());
                }
            }
            case 3 -> {
                op = PAInput.chooseOption("Lista de Propostas: ", "Autopropostas de alunos", "Propostas de docentes", "Propostas Disponiveis", "Propostas Atribuidas");
                switch (op) {
                    case 1 -> System.out.println(fsm.listaAutopropostas());
                    case 2 -> System.out.println(fsm.listaProjetos());
                    case 3 -> System.out.println(fsm.listaPropostasDisp());
                    case 4 -> System.out.println(fsm.listaPropostasAtribuidas());
                }
            }
            case 4 -> {
                String filename = sc.nextLine();
                fsm.exportaCsvFase3(filename);
            }
            case 5 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 6 -> {
                if (!fsm.fecharFaseAtribuiPropostas()) {
                    System.err.println("Não pode fechar a fase atual devido à anterior não estar fechada");
                } else System.out.println("Fase Fechada com Sucesso");
            }
            case 7 -> fsm.regressaFaseAnterior();
            case 8 -> fsm.avancarFase();
            case 9 -> finish = true;
        }
    }

    private void atribuicaoOrientadorUI() throws Exception {
        int op = PAInput.chooseOption("Atribuição Orientadores", "Gere Atribuição de Orientadores", "Obtenção Dados de Orientadores", "Exportar dados", "Fechar fase", "Regressa Fase Anterior", "Avançar Fase", "Save", "Quit");
        switch (op) {
            case 1 -> {
                op = PAInput.chooseOption("Comandos Atribuicao Orientador", "Atribuição automática de Proponentes de Projeto", "Atribuição manual de proponente de projeto",
                        "Consulta Proponente Projeto", "Altera Proponente Projeto", "Remove Proponente Projeto manualmente", "Undo", "Redo");
                switch (op) {
                    case 1 -> {
                        if (!fsm.atribuiProponentesProjeto())
                            System.out.println("Não foi possivel atribuir orientadores");
                    }
                    case 2 -> {
                        System.out.println("Digite o email do docente que quer atribuir");
                        String email = sc.next();
                        System.out.println("Digite o cod propostaque quer atribuir");
                        String cod = sc.next();
                        if (!fsm.atribuiProponenteManual(cod,email))
                            System.out.println("Email do docente já é proponente ou é invalido");
                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }
                    case 5-> {
                        System.out.println("Digite o email do docente que quer remover");
                        String email = sc.next();
                        System.out.println("Digite o codigo proposto quer remover");
                        String cod = sc.next();
                        if (!fsm.removeProponenteManual(cod,email))
                            System.out.println("Email do docente já é proponente ou é invalido");
                    }
                    case 6-> fsm.undo();
                    case 7-> fsm.redo();
                }
            }
            case 2 -> {
                int opt = PAInput.chooseOption("Dados Att Orientadores", "Lista Estudantes Proposta e Orientador", "Lista Estudantes Proposta sem Orientador", "Numero orientacoes");
                switch (opt) {
                    case 1 -> System.out.println(fsm.listaAlunoComOrientador());
                    case 2 -> System.out.println(fsm.listaAlunoSemOrientador());
                    case 3 -> {
                        System.out.println(fsm.contaOrientacoes());
                        System.out.println(fsm.listanOrientacoes());
                    }
                }
            }
            case 3 -> {
                String filename = sc.nextLine();
                fsm.exportaCsvFase4(filename);
            }
            case 4 -> fsm.fecharFaseOrientador();
            case 5 -> fsm.regressaFaseAnterior();
            case 6 -> fsm.avancarFase();
            case 7 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 8 -> finish = true;
        }
    }

    private void consultaUI() {
        int opt = PAInput.chooseOption("Consulta", "Estudantes atribuidos", "Estudantes não atribuidos mas candidatos", "Propostas disponiveis", "Propostas atribuidas", "Orientacoes", "Outros dados", "Save", "Quit");
        switch (opt) {
            case 1 -> System.out.println(fsm.listaAlunosComProposta());
            case 2 -> System.out.println(fsm.listaAlunosComCandSemProp());
            case 3 -> System.out.println(fsm.propostasDisponiveis());

            case 4 -> System.out.println(fsm.propostasAtribuidas());
            case 5 -> System.out.println(fsm.orientacoesDocente());
            case 6 -> {
            }
            case 7 -> {
                System.out.println("Deseja gravar em que nome?");
                /*try {
                    fsm.save(sc.nextLine());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    return;
                }*/
                System.out.println("Gravado com sucesso");
                finish = true;
            }
            case 8 -> finish = true;
        }
    }
}
