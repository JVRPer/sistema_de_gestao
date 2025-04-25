<h1 align='center'>
  Sistema de Gestão de Pacientes e Consultas
</h1>

### To Do 🛣️:

- Sistema de subir resultados das consultas:
    > Implementação de uma opção no menu, ao selecionar ela, pedirá o **CPF** do paciente, nome do médico e a descrição do resultado.
- Sistema para pagamentos, adicionando formas como Crédito, Débito e PIX:
    > Ao terminar de agendar a consulta, o usuário seria levado para uma tela de pagamento, aonde seria apresentado o valor da consulta e depois perguntaria a forma de pagamento.
- Sistema para criação de laudos médicos:
    > Implementação de uma opção no menu que levaria o médico a uma tela para criar e registrar um laudo médico, antes de criar, seria perguntado o CPF do médico para motivos de segurança, seria perguntado o CPF e Nome do paciente, pediria a descrição do laudo e por fim a assinatura do médico para fins de segurança.
- Sistema para confirmação de consultas:
    > Implementação de uma opção para confirmar consultas, deverá ser somente acessada pelos recepcionistas após um agendamento de consulta. Ao acessar a opção aparecerá uma lista de consultas esperando confirmação, a recepcionista deverá aceitar, logo será enviado para lista de confirmados.
- Sistema para cancelamento de consultas:
    > Implementação de uma opção para o cancelamento de uma consulta, ao acessar ela, pediria o CPF do Paciente, a data e a hora em que a consulta foi agendada.
- Sistema de histórico médico de um paciente:
    > Implementação de uma opção para ver o histórico médico do usuário no hospital, deverá ser acessado somente pelos médicos. Ao acessado, perguntará o CPF do médico primeiro e depois o CPF do paciente e retornará uma lista com o seu histórico.


### Done ✅:

- Sistema de Registro do Médico:
    > Opção criada para registrar um médico, pedirá o CPF dele, o nome e sua área de atuação.
    
    ### Criação da Class Médico:
    ```java
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
  ```
    ### Adicionamento da função registrarMedico na Class SistemaHospital
    ```java

    public void registrarMedico(String cpf, String nome, String especialidade) {
        Medico medicoEncontrado = null;
        for (Medico m : medicos) {
            if (m.getCpf().equals(cpf)) {
                medicoEncontrado = m;
                System.out.println("Médico já cadastrado!");
                break;
            }
        }
    ```
    ### Adicionamento da opção Registrar Médico no Main:
    ```java
    System.out.println("5 - Registrar médico");

    else if (opcao == 5) {
                System.out.println("Digite o CPF do médico: ");
                String cpfMedico = scanner.nextLine();
                System.out.println("Digite o nome do médico:");
                String medico = scanner.nextLine();
                System.out.println("Digite a especialidade do médico:");
                String especialidade = scanner.nextLine();
                sistema.registrarMedico(cpfMedico, medico, especialidade);

    ```

  
- Sistema para listar as consultas de um médico:
    > Adicionado opção para ver as consultas relacionadas ao um médico. Ao clicar na opção, pedirá somente o CPF do médico.

    ### Criado a Class ConsultaMedico:
    ```java
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
    ```

    ### Adicionado a função listarConsultaPaciente no SistemaHospital:
    ```java
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
    ```
    ### Adicionado a opção no Main:
    ```java
    else if (opcao == 6) {
                System.out.println("Digite o CPF do médico:");
                String cpfMedico = scanner.nextLine();
                sistema.listarConsultasMedico(cpfMedico);
    ```
