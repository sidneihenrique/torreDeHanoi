import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bem-vindo ao Jogo de Pilhas!");
        System.out.print("Informe o tamanho das pilhas: ");
        int tamanhoPilhas = scanner.nextInt();

        Stack pilha1 = new Stack();
        Stack pilha2 = new Stack();
        Stack pilha3 = new Stack();

        // Preenche pilha1 com números aleatórios entre 1 e 100
        for (int i = 0; i < tamanhoPilhas; i++) {
            pilha1.push(random.nextInt(100) + 1);
        }

        System.out.println("Pilha 1 (inicial):");
        printStack(pilha1);

        System.out.print("Deseja ordenar em ordem crescente (1) ou decrescente (2)? ");
        int ordem = scanner.nextInt();

        boolean ordenada = false;
        int movimentos = 0;

        while (!ordenada) {
          System.out.print("Escolha a pilha de origem (1, 2, 3, 0 para sair): ");
          int origem = scanner.nextInt();
      
          if (origem == 0) {
              System.out.println("Jogo encerrado.");
              break;
          }
      
          // Verifica se a pilha de origem não está vazia
          if (isEmptyPilha(origem, pilha1, pilha2, pilha3)) {
              System.out.println("A pilha de origem está vazia. Escolha outra pilha.");
              continue;
          }
      
          System.out.print("Escolha a pilha de destino (1, 2, 3): ");
          int destino = scanner.nextInt();
      
          if (movimentoValido(origem, destino)) {
              int numeroMovido = movePilha(origem, destino, pilha1, pilha2, pilha3);
              movimentos++;
              System.out.println("Pilha 1:");
              printStack(pilha1);
              System.out.println("Pilha 2:");
              printStack(pilha2);
              System.out.println("Pilha 3:");
              printStack(pilha3);
      
              if (verificaOrdenacao(pilha1, ordem)) {
                  ordenada = true;
                  System.out.println("Ordenação concluída em " + movimentos + " jogadas.");
                  break;
              }
          } else {
              System.out.println("Movimento inválido.");
          }
      }


    }

    public static boolean movimentoValido(int origem, int destino) {
        return (origem >= 1 && origem <= 3 && destino >= 1 && destino <= 3 && origem != destino);
    }

    public static int movePilha(int origem, int destino, Stack pilha1, Stack pilha2, Stack pilha3) {
        Stack pilhaOrigem, pilhaDestino;
        if (origem == 1) { pilhaOrigem = pilha1; }
        else if (origem == 2) { pilhaOrigem = pilha2; }
        else { pilhaOrigem = pilha3; }

        if (destino == 1) { pilhaDestino = pilha1; }
        else if (destino == 2) { pilhaDestino = pilha2; }
        else { pilhaDestino = pilha3; }

        int numeroMovido = pilhaOrigem.pop();
        pilhaDestino.push(numeroMovido);
        return numeroMovido;
    }

    public static boolean verificaOrdenacao(Stack pilha, int ordem) {
        if (pilha.isEmpty()) {
            return false;
        }

        int anterior = pilha.pop();
        boolean ordenada = true;

        while (!pilha.isEmpty()) {
            int atual = pilha.pop();
            if (ordem == 1) {
                if (anterior > atual) {
                    ordenada = false;
                    break;
                }
            } else {
                if (anterior < atual) {
                    ordenada = false;
                    break;
                }
            }
            anterior = atual;
        }

        // Restaura os valores originais da pilha
        while (!pilha.isEmpty()) {
            int valor = pilha.pop();
            if (valor != -1) {
                pilha.push(valor);
            }
        }

        return ordenada;
    }

    public static void printStack(Stack stack) {
        Stack temp = new Stack();
        while (!stack.isEmpty()) {
            int valor = stack.pop();
            if (valor != -1) {
                System.out.print(valor + " ");
                temp.push(valor);
            }
        }

        while (!temp.isEmpty()) {
            int valor = temp.pop();
            if (valor != -1) {
                stack.push(valor);
            }
        }
        System.out.println();
    }

    public static boolean isEmptyPilha(int pilhaEscolhida, Stack pilha1, Stack pilha2, Stack pilha3) {
      // Verifica qual pilha foi escolhida
      Stack pilhaSelecionada;
      if (pilhaEscolhida == 1) {
          pilhaSelecionada = pilha1;
      } else if (pilhaEscolhida == 2) {
          pilhaSelecionada = pilha2;
      } else {
          pilhaSelecionada = pilha3;
      }
  
      // Verifica se a pilha selecionada está vazia
      return pilhaSelecionada.isEmpty();
}

}
