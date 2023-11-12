import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Recepcionista extends Pessoa {

    private Paciente paciente;
    private Medico medico;
    private List<Agenda> listaAgendamento = new ArrayList<>();

    public Recepcionista(String _nome, int _cpf, int _telefone, String _email, Paciente paciente, Medico medico) {
        super(_nome, _cpf, _telefone, _email);

        this.paciente = paciente;
        this.medico = medico;
    }

    public void displayListaAgendamento(Medico m) {
        System.out.println("Lista de agendamentos - Médico: " + m.getNome());

        Iterator<Agenda> iterator = listaAgendamento.iterator();
        while (iterator.hasNext()) {
            Agenda agenda = iterator.next();
            System.out.println(agenda.getData() + "-" + agenda.getHora() + "h");
        }
        System.out.println();
    }

    public void agendaConsulta(boolean solicitacao, Paciente p, Medico m, String data, int hora, int minutos) {
        if (solicitacao) {
            String horas = Integer.toString(hora) + ":" + Integer.toString(minutos);
            Agenda agenda = new Agenda(data, horas, p, m);

            boolean horarioDisponivel = listaAgendamento.stream()
                    .anyMatch(a -> a.getData().equals(agenda.getData()) && a.getHora().equals(agenda.getHora()));

            if (horarioDisponivel) {
                System.out.println("------------------------------");
                System.out.println("Esse horario nao esta disponivel");
                System.out.println("------------------------------");
            } else {
                listaAgendamento.add(agenda);
                System.out.println("Consulta agendada com sucesso");
                agenda.displayAgendamento();
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("O paciente nao solicitou nenhuma consulta");
        }
    }
}
