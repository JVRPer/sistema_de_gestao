import java.util.ArrayList;
import java.util.Scanner;

class Paciente {
    private String nome;
    private String cpf;
    private int idade;
    private String telefone;

    public Paciente(String nome, String cpf, int idade, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void exibirInformacoes() {
        System.out.println("Paciente: " + nome + " | CPF: " + cpf + " | Idade: " + idade + " | Telefone: " + telefone);
    }
}

class Consulta {
    private Paciente paciente;
    private String data;
    private String horario;
    private String especialidade;
    private String medico;

    public Consulta(Paciente paciente, String data, String horario, String especialidade, String medico) {
        this.paciente = paciente;
        this.data = data;
        this.horario = horario;
        this.especialidade = especialidade;
        this.medico = medico;
    }

    public String getCpfPaciente() {
        return paciente.getCpf();
    }

    public void exibirDetalhes() {
        System.out.println("Consulta com " + medico + " | Especialidade: " + especialidade +
                " | Data: " + data + " | Horário: " + horario);
        paciente.exibirInformacoes();
    }
}

class Medico {
    private String nome;
    private String cpf;
    private String especialidade;

    public Medico(String nome, String cpf, String especialidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
    }

    public String getMedico() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void exibirInformacoes() {
        System.out.println("Médico: " + nome + " | CPF: " + cpf + " | Especialidade: " + especialidade);
    }
}

class ConsultaMedico {
    public Medico medico;
    private String cpf;
    private String data;
    private String horario;
    private String especialidade;

    public ConsultaMedico(Medico medico, String cpf, String data, String horario, String especialidade, String paciente) {
        this.medico = medico;
        this.cpf = cpf;
        this.data = data;
        this.horario = horario;
        this.especialidade = especialidade;
    }

    public String getMedico() {
        return medico.getCpf();
    }

    public void exibirDetalhes() {
        System.out.println("Consulta com: " + medico.getMedico() + " | Paciente: " + cpf + " | Data: " + data +
                " | Horário: " + horario + " | Especialidade: " + especialidade);
    }
}

class SistemaHospital {
    private ArrayList<Paciente> pacientes = new ArrayList<>();
    private ArrayList<Consulta> consultas = new ArrayList<>();
    private ArrayList<Medico> medicos = new ArrayList<>();
    private ArrayList<ConsultaMedico> consultasm = new ArrayList<>();

    public void cadastrarPaciente(String nome, String cpf, int idade, String telefone) {
        pacientes.add(new Paciente(nome, cpf, idade, telefone));
        System.out.println("Paciente cadastrado com sucesso!");
    }

    public void agendarConsulta(String cpf, String data, String horario, String especialidade, String medico) {
        Paciente pacienteEncontrado = null;
        Medico medicoEncontrado = null;

        for (Medico m : medicos) {
            if (m.getMedico().equals(medico)) {
                medicoEncontrado = m;
                break;
            }
        }

        if (medicoEncontrado == null) {
            System.out.println("Médico não encontrado!");
        }

        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpf)) {
                pacienteEncontrado = p;
                break;
            }
        }

        if (pacienteEncontrado != null && medicoEncontrado != null) {
            consultas.add(new Consulta(pacienteEncontrado, data, horario, especialidade, medico));
            consultasm.add(new ConsultaMedico(medicoEncontrado, cpf, data, horario, especialidade, medico));
            System.out.println("Consulta agendada com sucesso!");
        } else if (pacienteEncontrado == null) {
            System.out.println("Paciente não encontrado!");
        }
        else if (medicoEncontrado == null) {
            System.out.println("Médico não encontrado!");
        }
    }

    public void registrarMedico(String cpf, String nome, String especialidade) {
        Medico medicoEncontrado = null;
        for (Medico m : medicos) {
            if (m.getCpf().equals(cpf)) {
                medicoEncontrado = m;
                System.out.println("Médico já cadastrado!");
                break;
            }
        }

        if (medicoEncontrado == null) {
            medicos.add(new Medico(nome, cpf, especialidade));
            System.out.println("Médico cadastrado com sucesso!");
        }
    }

    public void listarConsultasPaciente(String cpf) {
        boolean encontrou = false;
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf)) {
                c.exibirDetalhes();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para este CPF.");
        }
    }

    public void registrarResposta(String cpf, String resposta) {
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf)) {
                System.out.println("Resposta registrada: " + resposta);
                return;
            }
        }
        System.out.println("Nenhuma consulta encontrada para este CPF.");
    }

    public void subirRespostaConsulta(String cpf, String resultado) {
        for (Consulta c : consultas) {
            if (c.getCpfPaciente().equals(cpf)) {
                System.out.println("Resultado da consulta: " + resultado);
                return;
            }
        }
        System.out.println("Nenhuma consulta encontrada para este CPF.");
    }

    public void listarConsultasMedico(String cpfMedico) {
        boolean encontrou = false;
        for (ConsultaMedico cm : consultasm) {
            if (cm.getMedico().equals(cpfMedico)) {
                encontrou = true;
                cm.exibirDetalhes();
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma consulta encontrada para este CPF.");

        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaHospital sistema = new SistemaHospital();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar Paciente");
            System.out.println("2 - Agendar Consulta");
            System.out.println("3 - Listar Consultas de um Paciente");
            System.out.println("4 - Subir uma consulta");
            System.out.println("5 - Registrar médico");
            System.out.println("6 - Listar consultas de um médico");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                sistema.cadastrarPaciente(nome, cpf, idade, telefone);

            } else if (opcao == 2) {
                System.out.print("CPF do paciente: ");
                String cpf = scanner.nextLine();
                System.out.print("Data (dd/mm/aaaa): ");
                String data = scanner.nextLine();
                System.out.print("Horário (hh:mm): ");
                String horario = scanner.nextLine();
                System.out.print("Especialidade: ");
                String especialidade = scanner.nextLine();
                System.out.print("Médico: ");
                String medico = scanner.nextLine();
                sistema.agendarConsulta(cpf, data, horario, especialidade, medico);

            } else if (opcao == 3) {
                System.out.print("CPF do paciente: ");
                String cpf = scanner.nextLine();
                sistema.listarConsultasPaciente(cpf);

            } else if (opcao == 4) {
                System.out.print("CPF do paciente: ");
                String cpf = scanner.nextLine();
                System.out.println("Descreva o resultado sobre a consulta:");
                String resultado = scanner.nextLine();
                sistema.subirRespostaConsulta(cpf, resultado);
            } else if (opcao == 5) {
                System.out.println("Digite o CPF do médico: ");
                String cpfMedico = scanner.nextLine();
                System.out.println("Digite o nome do médico:");
                String medico = scanner.nextLine();
                System.out.println("Digite a especialidade do médico:");
                String especialidade = scanner.nextLine();
                sistema.registrarMedico(cpfMedico, medico, especialidade);
            } else if (opcao == 6) {
                System.out.println("Digite o CPF do médico:");
                String cpfMedico = scanner.nextLine();
                sistema.listarConsultasMedico(cpfMedico);
            } else {
                System.out.println("Opção inválida! Tente novamente.");
                break;
            }
        }
        scanner.close();
    }
}