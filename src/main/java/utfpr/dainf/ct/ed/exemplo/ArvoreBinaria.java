package utfpr.dainf.ct.ed.exemplo;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * Exemplo de implementação de árvore binária.
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós na árvore
 */
public class ArvoreBinaria<E> {

    protected E valor;
    protected ArvoreBinaria<E> esquerda;
    protected ArvoreBinaria<E> direita;

    // para percurso iterativo
    private boolean inicio = true;
    private Stack<ArvoreBinaria<E>> pilha;
    private ArrayDeque<ArvoreBinaria<E>> fila;
    private ArvoreBinaria<E> ultimoVisitado;
    private ArvoreBinaria<E> noPos;

    //https://www.ime.usp.br/~pf/mac0122-2002/aulas/trees.html
    public static int calculaAltura(ArvoreBinaria no) {
        int alturaEsquerda, alturaDireita;
        if (no == null) {
            return -1;
        }

        alturaEsquerda = calculaAltura(no.esquerda);
        alturaDireita = calculaAltura(no.direita);

        if (alturaEsquerda > alturaDireita) {
            return alturaEsquerda + 1;
        } else {
            return alturaDireita + 1;
        }
    }

    /**
     * Cria uma árvore binária com valor nulo na raiz.
     */
    public ArvoreBinaria() {
    }

    /**
     * Cria uma árvore binária com valor {@code valor} na raiz.
     *
     * @param valor O valor do nó raiz
     */
    public ArvoreBinaria(E valor) {
        this.valor = valor;
    }

    /**
     * Insere uma subárvore à esquerda deste nó. A subárvore à esquerda deste nó
     * é inserida na folha mais à esquerda da subárvore inserida.
     *
     * @param a A subárvore a ser inserida.
     * @return A subárvore inserida.
     */
    public ArvoreBinaria<E> insereEsquerda(ArvoreBinaria<E> a) {
        ArvoreBinaria<E> e = esquerda;
        ArvoreBinaria<E> x = a;
        esquerda = a;
        while (x.esquerda != null) {
            x = x.esquerda;
        }
        x.esquerda = e;
        return a;
    }

    /**
     * Insere uma subárvore à direita deste nó. A subárvore à direita deste nó é
     * inserida na folha mais à direita da subárvore inserida.
     *
     * @param a A subárvore a ser inserida.
     * @return A subárvore inserida.
     */
    public ArvoreBinaria<E> insereDireita(ArvoreBinaria<E> a) {
        ArvoreBinaria<E> d = direita;
        ArvoreBinaria<E> x = a;
        direita = a;
        while (x.direita != null) {
            x = x.direita;
        }
        x.direita = d;
        return a;
    }

    /**
     * Implementação padrão que exibe o valor armazenado no nó usando o método
     * {@code toString()}. Pode ser sobrecarregado em classes derivadas para
     * implementar outras formas de visita.
     *
     * @param no O nó a ser visitado
     */
    protected void visita(ArvoreBinaria<E> no) {
        System.out.print(" " + no.valor);
    }

    /**
     * Visita os nós da subárvore em-ordem.
     *
     * @param raiz A raiz da subárvore
     */
    public void visitaEmOrdem(ArvoreBinaria<E> raiz) {
        if (raiz != null) {
            visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            visitaEmOrdem(raiz.direita);
        }
    }

    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }

    /**
     * Visita os nós da subárvore em pre-ordem.
     *
     * @param raiz A raiz da subárvore
     */
    public void visitaPreOrdem(ArvoreBinaria<E> raiz) {
        if (raiz != null) {
            visita(raiz);
            visitaPreOrdem(raiz.esquerda);
            visitaPreOrdem(raiz.direita);
        }
    }
    
    
    public void imprimePreOrdem(ArvoreBinaria<E> raiz, int nivel) {
        if (raiz != null) {
            for(int i=0;i<nivel;i++){
                System.out.print('*');
            }
            System.out.println(raiz.valor);
            imprimePreOrdem(raiz.esquerda,nivel+1);
            imprimePreOrdem(raiz.direita,nivel+1);
        }
    }

    /**
     * Visita os nós da árvore em pre-ordem a partir da raiz.
     */
    public void visitaPreOrdem() {
        visitaPreOrdem(this);
    }

    /**
     * Visita os nós da subárvore em pre-ordem.
     *
     * @param raiz A raiz da subárvore
     */
    public void visitaPosOrdem(ArvoreBinaria<E> raiz) {
        if (raiz != null) {
            visitaPosOrdem(raiz.esquerda);
            visitaPosOrdem(raiz.direita);
            visita(raiz);
        }
    }

    /**
     * Visita os nós da árvore em pre-ordem a partir da raiz.
     */
    public void visitaPosOrdem() {
        visitaPosOrdem(this);
    }

    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }

    private void inicializaFila() {
        if (fila == null) {
            fila = new ArrayDeque<>();
        }
    }

    /**
     * Reinicia o percurso a partir do início. Deve ser chamado após percorrer
     * toda a árvore para realizar novo percurso ou para voltar ao início a
     * qualquer momento.
     */
    public void reinicia() {
        inicializaPilha();
        inicializaFila();
        pilha.clear();
        fila.clear();
        ultimoVisitado = this;
        inicio = true;
    }

    /**
     * Retorna o valor do próximo nó em-ordem.
     *
     * @return O valor do próximo nó em-ordem.
     */
    public ArvoreBinaria<E> proximoEmOrdem() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            ultimoVisitado = ultimoVisitado.direita;
        }
        return resultado;
    }

    /**
     * Retorna o próximo nó em pré-ordem.
     *
     * @return O próximo nó em pré-ordem.
     */
    public ArvoreBinaria<E> proximoPreOrdem() {
        //retorno
        ArvoreBinaria<E> resultado = null;

        //reseta a pilha
        if (inicio) {
            reinicia();
            inicio = false;
        }

        //coloca o ultimo (atual) na pilha
        if (ultimoVisitado != null) {
            pilha.push(ultimoVisitado);
        }
        //se ela nao esticer vazia
        if (!pilha.isEmpty()) {
            //atualiza o ultimo visitado para ser o de cima da pilha
            ultimoVisitado = pilha.pop();
            //atualiza o de saida
            resultado = ultimoVisitado;
            //adicionando o da direita e da esquerda na pilha
            if (ultimoVisitado != null) {
                if (ultimoVisitado.direita != null) {
                    pilha.push(ultimoVisitado.direita);
                }
                if (ultimoVisitado.esquerda != null) {
                    pilha.push(ultimoVisitado.esquerda);
                }
            }
            //atualiza o ultimo visitado q na verdade eh o proximo da pilha
            ultimoVisitado = !pilha.isEmpty() ? pilha.pop() : null;
        }
        return resultado;
    }

    /**
     * Retorna o próximo nó em pós-ordem.
     *
     * @return O próximo nó em pós-ordem.
     */
    public ArvoreBinaria<E> proximoPosOrdem() {
        //retorno
        ArvoreBinaria<E> resultado = null;
        boolean primeira = inicio;
        //reseta a pilha
        if (inicio) {
            reinicia();
            inicio = false;
        }

        if (pilha.isEmpty() && !primeira) {
            return null;
        } else {
            resultado = pilha.isEmpty() ? ultimoVisitado : pilha.pop();
        }

        while (true) {
            if (resultado.esquerda != null
                    || resultado.direita != null) {

                if (resultado.esquerda != null
                        && resultado.esquerda != ultimoVisitado
                        && resultado.direita != ultimoVisitado) {
                    pilha.push(resultado);
                    resultado = resultado.esquerda;
                } else if (resultado.direita != null
                        && resultado.direita != ultimoVisitado) {
                    pilha.push(resultado);
                    resultado = resultado.direita;
                } else {
                    ultimoVisitado = resultado;
                    return resultado;
                }
            } else {
                ultimoVisitado = resultado;
                return resultado;
            }
        }

        /*if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            resultado = ultimoVisitado = pilha.pop();
        
        
        
        
        
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            resultado = ultimoVisitado = pilha.pop();
            
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.direita;
            }
            pilha.pop();
            ultimoVisitado = pilha.pop();
        }
        return resultado;*/
 /* noPos = this;
        ultimoVisitado=null;
        
        if(noPos!=null || !pilha.isEmpty()){
            while(noPos!=null){
                pilha.push(noPos);
                noPos = noPos.esquerda;
            }
            resultado = pilha.pop();
            while(resultado.direita != null && ultimoVisitado != resultado.direita){
                noPos = resultado.direita;
            }
            ultimoVisitado = pilha.pop();

        }
        
        return resultado;*/
 /*if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.esquerda;
            }
            resultado = ultimoVisitado = pilha.pop();
            
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = ultimoVisitado.direita;
            }
            pilha.pop();
            ultimoVisitado = pilha.pop();
        }
        return resultado;*/
    }

    /**
     * Retorna o próximo nó em nível.
     *
     * @return O próximo nó em nível.
     */
    public ArvoreBinaria<E> proximoEmNivel() {
        ArvoreBinaria<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
            fila.add(this);
        }

        if (!fila.isEmpty()) {

            resultado = fila.poll();

            if (resultado.esquerda != null) {
                fila.add(resultado.esquerda);
            }
            if (resultado.direita != null) {
                fila.add(resultado.direita);
            }
        }
        return resultado;
    }

    /**
     * Retorna o valor armazenado no nó.
     *
     * @return O valor armazenado no nó.
     */
    public E getValor() {
        return valor;
    }

    /**
     * Atribui um valor ao nó.
     *
     * @param valor O valor a ser atribuído ao nó.
     */
    public void setValor(E valor) {
        this.valor = valor;
    }

    /**
     * Retorna a árvore esqueda.
     *
     * @return A árvore esquerda.
     */
    protected ArvoreBinaria<E> getEsquerda() {
        return esquerda;
    }

    /**
     * Retorna a árvore direita.
     *
     * @return A árvore direita.
     */
    protected ArvoreBinaria<E> getDireita() {
        return direita;
    }

    /**
     * Inicializa a árvore esquerda.
     *
     * @param esquerda A árvore esquerda.
     */
    protected void setEsquerda(ArvoreBinaria<E> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Inicializa a árvore direita.
     *
     * @param direita A árvore direita.
     */
    protected void setDireita(ArvoreBinaria<E> direita) {
        this.direita = direita;
    }

}
