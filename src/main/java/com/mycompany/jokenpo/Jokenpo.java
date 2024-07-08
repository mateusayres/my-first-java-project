package com.mycompany.jokenpo;

import java.util.Scanner;
import java.util.Random;

/**
 * @author Sr. Ayres
 */
public class Jokenpo {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int modo, contagem, rodada, numeroPessoa, numeroPessoa2, pontuacaoPessoa, pontuacaoPessoa2, pontuacaoComputador, empate, verificacaoDePontuacao;
        String jogadaPessoa, jogadaPessoa2, jogadaComputador, verificacaoDeJogada;
        char jogarDeNovo;
        contagem = 1;
        pontuacaoPessoa = 0;
        pontuacaoPessoa2 = 0;
        pontuacaoComputador = 0;
        empate = 0;

        modo = intro0();
        rodada = intro1();

        do {
            intro2();
            switch (modo) {
                case 1:
                    do {
                        numeroPessoa = verificador();
                        jogadaPessoa = jogadaPess(numeroPessoa);
                        jogadaComputador = jogadaComput();
                        System.out.print(jogadaPessoa + " -- " + jogadaComputador + " ");
                        verificacaoDeJogada = verificJogada(jogadaPessoa, jogadaComputador);
                        System.out.println(verificacaoDeJogada);
                        verificacaoDePontuacao = verificPontuacao(verificacaoDeJogada);
                        incentivo(verificacaoDePontuacao);
                        switch (verificacaoDePontuacao) {

                            case 0:
                                rodada++;
                                empate++;
                                break;
                            case 1:
                                pontuacaoPessoa++;
                                break;
                            case 2:
                                pontuacaoComputador++;
                                break;
                        }

                        System.out.println("      //////// Placar: " + pontuacaoPessoa + " -- " + pontuacaoComputador + " \\\\\\\\\\\\\\\\");
                        contagem++;
                    } while (contagem <= rodada);
                    resultado(pontuacaoPessoa, pontuacaoComputador);
                    break;
                case 2:
                    do {
                        numeroPessoa = verificadorplayer1();
                        jogadaPessoa = jogadaPess(numeroPessoa);
                        numeroPessoa2 = verificadorplayer2();
                        jogadaPessoa2 = jogadaPess2(numeroPessoa2);
                        System.out.print(jogadaPessoa + " -- " + jogadaPessoa2 + " ");
                        verificacaoDeJogada = verificJogada2(jogadaPessoa, jogadaPessoa2);
                        System.out.println(verificacaoDeJogada);
                        verificacaoDePontuacao = verificPontuacao2(verificacaoDeJogada);
                        switch (verificacaoDePontuacao) {

                            case 0:
                                rodada++;
                                empate++;
                                break;
                            case 1:
                                pontuacaoPessoa++;
                                break;
                            case 2:
                                pontuacaoPessoa2++;
                                break;
                        }

                        System.out.println("      //////// Placar: " + pontuacaoPessoa + " -- " + pontuacaoPessoa2 + " \\\\\\\\\\\\\\\\");
                        contagem++;
                    } while (contagem <= rodada);
                    resultado2(pontuacaoPessoa, pontuacaoPessoa2);
                    break;
            }
            //Reset dos Parâmetros.
            rodada = rodada - empate;
            empate = 0;
            contagem = 1;
            pontuacaoPessoa = 0;
            pontuacaoPessoa2 = 0;
            pontuacaoComputador = 0;
            System.out.println("");
            System.out.print("Deseja jogar novamente? (S/N): ");
            jogarDeNovo = entrada.next().charAt(0);

        } while (jogarDeNovo == 's' || jogarDeNovo == 'S');
        System.out.println("");
        System.out.print("      //////// Até a próxima! \\\\\\\\\\\\\\\\");
        //Fim do Jogo.
    }

    //Método feito para a introdução 0 do Jogo, que verifica se o modo está dentro dos números esperados.
    public static int intro0() {
        Scanner entrada = new Scanner(System.in);
        int modo;
        boolean a;
        System.out.println("-- Vamos jogar Jokenpô? --");
        System.out.println("");
        System.out.println("Selecione o modo de jogo:");
        System.out.println("(1) Um Jogador = Player vs Computador.");
        System.out.println("(2) Dois Jogadores = Player vs Player.");

        do {
            System.out.println("");
            System.out.print("Digite o modo: ");
            modo = entrada.nextInt();
            a = (modo > 0 && modo < 3);
            if (a == false) {
                System.out.println("Número Incorreto. (Insira 1 ou 2).");
            }
        } while (a == false);
        return modo;
    }

    //Método feito para a introdução 1 do Jogo, que invalida caso o número escolhido seja par.
    public static int intro1() {
        Scanner entrada = new Scanner(System.in);
        int rodada;
        System.out.println("");
        System.out.println("Informe a quantidade de rodadas a serem");
        System.out.print("jogadas, (Só vale números ÍMPARES): ");
        rodada = entrada.nextInt();

        while (rodada % 2 == 0) {
            System.out.print("Número Incorreto. (Só vale números ÍMPARES): ");
            rodada = entrada.nextInt();
        }
        return rodada;
    }

    //Método feito para a introdução 2 do Jogo. (Somente texto.)
    public static void intro2() {
        System.out.println("");
        System.out.println("-- Perfeito! --");
        System.out.println("");
        System.out.println("Observe as regras:");
        System.out.println("- Pedra Ganha da Tesoura.");
        System.out.println("- Tesoura Ganha do Papel.");
        System.out.println("- Papel Ganha da Pedra.");
        System.out.println("");
        System.out.println("Digite (1) para Pedra.");
        System.out.println("Digite (2) para Papel.");
        System.out.println("Digite (3) para Tesoura.");
    }

    //Verificador de continuação do jogo, com repetição caso não seja (1, 2 ou 3).
    public static int verificador() {
        Scanner entrada = new Scanner(System.in);
        int num;
        boolean a;

        do {
            System.out.println("");
            System.out.print("Digite sua jogada: ");
            num = entrada.nextInt();
            a = (num <= 3 && num >= 1);
            if (a == false) {
                System.out.println("Número Incorreto. (Insira 1, 2 ou 3).");
            }
        } while (a == false);
        return num;
    }

    //Método para retornar a jogada desejada da pessoa.
    public static String jogadaPess(int numeroPessoa) {
        if (numeroPessoa == 1) {
            return "Pedra";
        } else {
            if (numeroPessoa == 2) {
                return "Papel";
            } else { //Só pode ser Tesoura, pois esta amarrado a repetição caso não seja (1, 2 ou 3). 
                return "Tesoura";
            }
        }
    }

    //Método para retornar a jogada aleatória do computador.
    public static String jogadaComput() {
        Random random = new Random();
        int comput;
        comput = random.nextInt(3);

        if (comput == 0) {
            return "Pedra";
        } else {
            if (comput == 1) {
                return "Tesoura";
            } else {
                return "Papel";
            }
        }
    }

    //Método que verifica quem ganhou (Pedra, Papel ou Tesoura, e Você ou Computador).
    public static String verificJogada(String jogadaPessoa, String jogadaComputador) {
        if (jogadaPessoa.equals("Pedra")) {
            if (jogadaComputador.equals("Pedra")) {
                return "  -> Jogue de novo!"; //Empate
            } else {
                if (jogadaComputador.equals("Papel")) {
                    return "  -> Papel Ganhou! (Computador)";
                } else { //Só pode ser Tesoura.
                    return "  -> Pedra Ganhou! (Você)";
                }
            }
        }

        if (jogadaPessoa.equals("Papel")) {
            if (jogadaComputador.equals("Pedra")) {
                return "  -> Papel Ganhou! (Você)";
            } else {
                if (jogadaComputador.equals("Papel")) {
                    return "  -> Jogue de novo!"; //Empate
                } else { //Só pode ser Tesoura.
                    return "  -> Tesoura Ganhou! (Computador)";
                }
            }
        }

        if (jogadaPessoa.equals("Tesoura")) {
            if (jogadaComputador.equals("Pedra")) {
                return "  -> Pedra Ganhou! (Computador)";
            } else {
                if (jogadaComputador.equals("Papel")) {
                    return "  -> Tesoura Ganhou! (Você)";
                } else { //Só pode ser Tesoura.
                    return "  -> Jogue de novo!"; //Empate
                }
            }
        }
        return null;
    }

    //Verifica se você ganhou, perdeu ou empatou. (Utilizado na Pontuação e no Incentivo.)
    public static int verificPontuacao(String verificacaoDeJogada) {
        if (verificacaoDeJogada.contains("Você")) {
            return 1; //Ponto do Jogador (Você)
        } else {
            if (verificacaoDeJogada.contains("Computador")) {
                return 2; //Ponto do Computador (Computador)
            } else {
                return 0; //Empate, +1 na Rodada.
            }
        }
    }

    //Pega o resultado da "VerificPontuacao" e gera um retorno de Incentivo.
    public static void incentivo(int verificacaoDePontuacao) {
        switch (verificacaoDePontuacao) {
            case 0:
                incentivoEmpate();
                break;
            case 1:
                incentivoBom();
                break;
            case 2:
                incentivoRuim();
                break;
        }
    }

    //Procedimento que informa o resultado final da partida.
    public static void resultado(int pontuacaoPessoa, int pontuacaoComputador) {
        if (pontuacaoPessoa > pontuacaoComputador) {
            System.out.println("");
            System.out.println("Você GANHOU! Parabéns!!");
        } else {
            System.out.println("");
            System.out.println("Você PERDEU! Tente novamente :( ");
        }
    }

    //Método Banco de Incentivos de Empate Aleatório.
    public static void incentivoEmpate() {
        Random random = new Random();
        int empate;
        empate = random.nextInt(5);

        switch (empate) {
            case 0:
                System.out.println("- Parece que temos um empate!!");
                break;
            case 1:
                System.out.println("- Ninguém sai perdendo quando há um empate.");
                break;
            case 2:
                System.out.println("- Nenhum vencedor desta vez.");
                break;
            case 3:
                System.out.println("- A igualdade persiste. Quem vai quebrar o empate na próxima jogada?");
                break;
            case 4:
                System.out.println("- O resultado é um empate. Ambos os jogadores estão em sintonia.");
                break;
        }
    }

    //Método Banco de Incentivos Bons Aleatório.
    public static void incentivoBom() {
        Random random = new Random();
        int bom;
        bom = random.nextInt(10);

        switch (bom) {
            case 0:
                System.out.println("- Parabéns! Você é nota 1000!");
                break;
            case 1:
                System.out.println("- Logo será um Vencedor! Continue assim!");
                break;
            case 2:
                System.out.println("- Você é um mestre do Jokenpo!");
                break;
            case 3:
                System.out.println("- Ótima jogada! Você está arrasando!");
                break;
            case 4:
                System.out.println("- Uau! Acho que ninguém irá vencê-lo!");
                break;
            case 5:
                System.out.println("- Uau! Você domina o Jokenpo. Parabéns!");
                break;
            case 6:
                System.out.println("- Ganhar é só uma amostra do seu talento!");
                break;
            case 7:
                System.out.println("- Você é uma máquina do Jokenpo! Continue assim!");
                break;
            case 8:
                System.out.println("- Você tem o toque de Midas no Jokenpo. Parabéns pela vitória na jogada!");
                break;
            case 9:
                System.out.println("- Ninguém pode detê-lo! Continue vencendo!");
                break;
        }
    }

    //Método Banco de Incentivos Ruins Aleatório.
    public static void incentivoRuim() {
        Random random = new Random();
        int ruim;
        ruim = random.nextInt(10);

        switch (ruim) {
            case 0:
                System.out.println("- Não desanime! A próxima parada é a vitória!");
                break;
            case 1:
                System.out.println("- Não foi dessa vez, mas você vai conseguir!");
                break;
            case 2:
                System.out.println("- Continue tentando! O sucesso virá.");
                break;
            case 3:
                System.out.println("- Perder faz parte do jogo, mas você é um grande jogador.");
                break;
            case 4:
                System.out.println("- Lembre-se, a prática leva à perfeição. Continue jogando!");
                break;
            case 5:
                System.out.println("- Mesmo na derrota, você mostra sua habilidade no Jokenpo.");
                break;
            case 6:
                System.out.println("- Você é um competidor corajoso. A vitória virá em breve!");
                break;
            case 7:
                System.out.println("- A próxima vitória será ainda mais doce após essa derrota.");
                break;
            case 8:
                System.out.println("- Cada derrota é uma oportunidade de aprendizado. Continue evoluindo!");
                break;
            case 9:
                System.out.println("- As derrotas são temporárias, mas a sua determinação é eterna.");
                break;
        }
    }

    // ---------------------------------------------Modo 2 Players------------------------------------------------
    //Verificador de continuação do jogo player1, com repetição caso não seja (1, 2 ou 3).
    public static int verificadorplayer1() {
        Scanner entrada = new Scanner(System.in);
        int num;
        boolean a;

        do {
            System.out.println("");
            System.out.print("Digite sua jogada Player1: ");
            num = entrada.nextInt();
            a = (num <= 3 && num >= 1);
            if (a == false) {
                System.out.println("Número Incorreto. (Insira 1, 2 ou 3).");
            }
        } while (a == false);
        return num;
    }

    //Verificador de continuação do jogo player2, com repetição caso não seja (1, 2 ou 3).
    public static int verificadorplayer2() {
        Scanner entrada = new Scanner(System.in);
        int num2;
        boolean a;

        do {
            System.out.print("Digite sua jogada Player2: ");
            num2 = entrada.nextInt();
            a = (num2 <= 3 && num2 >= 1);
            if (a == false) {
                System.out.println("Número Incorreto. (Insira 1, 2 ou 3).");
            }
        } while (a == false);
        return num2;
    }

    //Método para retornar a jogada2 desejada da pessoa2.
    public static String jogadaPess2(int numeroPessoa2) {
        if (numeroPessoa2 == 1) {
            return "Pedra";
        } else {
            if (numeroPessoa2 == 2) {
                return "Papel";
            } else { //Só pode ser Tesoura, pois esta amarrado a repetição caso não seja (1, 2 ou 3). 
                return "Tesoura";
            }
        }
    }

    //Método que verifica quem ganhou (Pedra, Papel ou Tesoura, e Player1 ou Player2).
    public static String verificJogada2(String jogadaPessoa, String jogadaPessoa2) {
        if (jogadaPessoa.equals("Pedra")) {
            if (jogadaPessoa2.equals("Pedra")) {
                return "  -> Jogue de novo!"; //Empate
            } else {
                if (jogadaPessoa2.equals("Papel")) {
                    return "  -> Papel Ganhou! (Player2)";
                } else { //Só pode ser Tesoura.
                    return "  -> Pedra Ganhou! (Player1)";
                }
            }
        }

        if (jogadaPessoa.equals("Papel")) {
            if (jogadaPessoa2.equals("Pedra")) {
                return "  -> Papel Ganhou! (Player1)";
            } else {
                if (jogadaPessoa2.equals("Papel")) {
                    return "  -> Jogue de novo!"; //Empate
                } else { //Só pode ser Tesoura.
                    return "  -> Tesoura Ganhou! (Player2)";
                }
            }
        }

        if (jogadaPessoa.equals("Tesoura")) {
            if (jogadaPessoa2.equals("Pedra")) {
                return "  -> Pedra Ganhou! (Player2)";
            } else {
                if (jogadaPessoa2.equals("Papel")) {
                    return "  -> Tesoura Ganhou! (Player1)";
                } else { //Só pode ser Tesoura.
                    return "  -> Jogue de novo!"; //Empate
                }
            }
        }
        return null;
    }

    //Verifica quem ganhou, perdeu ou empatou. (Utilizado na Pontuação)
    public static int verificPontuacao2(String verificacaoDeJogada) {
        if (verificacaoDeJogada.contains("Player1")) {
            return 1; //Ponto do Jogador1
        } else {
            if (verificacaoDeJogada.contains("Player2")) {
                return 2; //Ponto do Jogador2
            } else {
                return 0; //Empate, +1 na Rodada.
            }
        }
    }

    //Procedimento que informa o resultado2 final da partida.
    public static void resultado2(int pontuacaoPessoa, int pontuacaoPessoa2) {
        if (pontuacaoPessoa > pontuacaoPessoa2) {
            System.out.println("");
            System.out.println("Player1  -- GANHOU! Parabéns!!");
            System.out.println("Player2  -- PERDEU! Tente novamente :( ");
        } else {
            System.out.println("");
            System.out.println("Player1  -- PERDEU! Tente novamente :( ");
            System.out.println("Player2  -- GANHOU! Parabéns!!");
        }
    }
}
