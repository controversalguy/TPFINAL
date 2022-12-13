package pt.isec.pa.apoio_poe.model.data.alunodata;

import pt.isec.pa.apoio_poe.model.data.alunodata.Aluno;

import java.util.Comparator;

public class ClassificacaoComparator implements Comparator <Aluno> {

    @Override
    public int compare(Aluno o1, Aluno o2) {
        return Double.compare(o2.getClassificacao(),o1.getClassificacao());
    }

}
