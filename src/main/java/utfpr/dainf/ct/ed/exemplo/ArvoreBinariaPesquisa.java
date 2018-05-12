package utfpr.dainf.ct.ed.exemplo;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * Exemplo de implementação de árvore binária de pesquisa.
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós da árvore
 */
public class ArvoreBinariaPesquisa<E extends Comparable<E>> extends ArvoreBinaria<E> {

    public ArvoreBinariaPesquisa<E> pai;
    protected Stack<ArvoreBinariaPesquisa<E>> pilha;
    /*protected ArvoreBinariaPesquisa<E> esquerda;
    protected ArvoreBinariaPesquisa<E> direita;*/
    protected ArvoreBinariaPesquisa<E> ultimoVisitado;

    // para percurso iterativo
    private boolean inicio = true;
    private ArvoreBinariaPesquisa<E> noPos;

    private void inicializaPilha() {
        if (pilha == null) {
            pilha = new Stack<>();
        }
    }

    public void reinicia() {
        inicializaPilha();
        pilha.clear();
        ultimoVisitado = this;
        inicio = true;
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
    protected void setEsquerda(ArvoreBinariaPesquisa<E> esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Inicializa a árvore direita.
     *
     * @param direita A árvore direita.
     */
    protected void setDireita(ArvoreBinariaPesquisa<E> direita) {
        this.direita = direita;
    }

    public ArvoreBinariaPesquisa<E> getRoot() {
        ArvoreBinariaPesquisa<E> root = this;

        while (root.pai != null) {
            root = root.pai;
        }
        return root;
    }

    /**
     * Insere uma subárvore à esquerda deste nó. A subárvore à esquerda deste nó
     * é inserida na folha mais à esquerda da subárvore inserida.
     *
     * @param a A subárvore a ser inserida.
     * @return A subárvore inserida.
     */
    public ArvoreBinariaPesquisa<E> insereEsquerda(ArvoreBinariaPesquisa<E> a) {
        ArvoreBinariaPesquisa<E> e = (ArvoreBinariaPesquisa<E>) esquerda;
        ArvoreBinariaPesquisa<E> x = a;
        esquerda = a;
        while (x.esquerda != null) {
            x = (ArvoreBinariaPesquisa<E>) x.esquerda;
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
    public ArvoreBinariaPesquisa<E> insereDireita(ArvoreBinariaPesquisa<E> a) {
        ArvoreBinariaPesquisa<E> d = (ArvoreBinariaPesquisa<E>) direita;
        ArvoreBinariaPesquisa<E> x = a;
        direita = a;
        while (x.direita != null) {
            x = (ArvoreBinariaPesquisa<E>) x.direita;
        }
        x.direita = d;
        return a;
    }

    /**
     * Visita os nós da subárvore em-ordem.
     *
     * @param raiz A raiz da subárvore
     */
    public void visitaEmOrdem(ArvoreBinariaPesquisa<E> raiz) {
        if (raiz != null) {
            visitaEmOrdem(raiz.esquerda);
            visita(raiz);
            visitaEmOrdem(raiz.direita);
        }
    }

    /**
     * Retorna o valor do próximo nó em-ordem.
     *
     * @return O valor do próximo nó em-ordem.
     */
    public ArvoreBinariaPesquisa<E> proximoEmOrdem() {
        ArvoreBinariaPesquisa<E> resultado = null;
        if (inicio) {
            reinicia();
            inicio = false;
        }
        if (!pilha.isEmpty() || ultimoVisitado != null) {
            while (ultimoVisitado != null) {
                pilha.push(ultimoVisitado);
                ultimoVisitado = (ArvoreBinariaPesquisa<E>) ultimoVisitado.esquerda;
            }
            ultimoVisitado = pilha.pop();
            resultado = ultimoVisitado;
            ultimoVisitado = (ArvoreBinariaPesquisa<E>) ultimoVisitado.direita;
        }
        return resultado;
    }

    /**
     * Visita os nós da árvore em-ordem a partir da raiz.
     */
    public void visitaEmOrdem() {
        visitaEmOrdem(this);
    }

    /**
     * Cria uma árvore com valor nulo na raiz.
     */
    public ArvoreBinariaPesquisa() {
    }

    /**
     * Cria uma árvore com o valor especificado na raiz.
     *
     * @param valor O valor armazenado na raiz.
     */
    public ArvoreBinariaPesquisa(E valor) {
        super(valor);
    }

    /**
     * Inicializa o nó pai deste nó.
     *
     * @param pai O nó pai.
     */
    protected void setPai(ArvoreBinariaPesquisa<E> pai) {
        this.pai = pai;
    }

    /**
     * Retorna o nó pai deste nó.
     *
     * @return O nó pai.
     */
    public ArvoreBinariaPesquisa<E> getPai() {
        return pai;
    }

    /**
     * OK Retorna o nó da árvore cujo valor corresponde ao especificado.
     *
     * @param valor O valor a ser localizado.
     * @return A raiz da subárvore contendo o valor ou {@code null}.
     */
    public ArvoreBinariaPesquisa<E> pesquisa(E valor) {
        /////////////////////////// A PORRA DO == TINHA QUE SER COMPATE TO]
        if (valor.compareTo(this.valor) == 0) {
            return this;
        } else if (valor.compareTo(this.valor) < 0) {
            if (this.esquerda == null) {
                return null;
                //}else return ((ArvoreBinariaPesquisa<E>)this.esquerda).pesquisa(valor);
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.esquerda).pesquisa(valor);
            }
        } else {
            if (this.direita == null) {
                return null;
                //}else return ((ArvoreBinariaPesquisa<E>)this.direita).pesquisa(valor);
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.direita).pesquisa(valor);
            }
        }
    }

    public ArvoreBinariaPesquisa<E> getMinimoRamo() // retorna um nó
    {
        ArvoreBinariaPesquisa<E> resultado = this;
        while (resultado.esquerda != null) { // o menor nó será o nó mais a esquerda, não precisa ser a folha!
            resultado = (ArvoreBinariaPesquisa) resultado.esquerda;
        }
        return resultado;
    }
    
    /**
     * OK Retorna o nó da árvore com o menor valor.
     *
     * @return A raiz da subárvore contendo o valor mínimo
     */
    public ArvoreBinariaPesquisa<E> getMinimo() // retorna um nó
    {
        ArvoreBinariaPesquisa<E> root = getRoot();
      
        ArvoreBinariaPesquisa<E> resultado = root;
        while (resultado.esquerda != null) { // o menor nó será o nó mais a esquerda, não precisa ser a folha!
            resultado = (ArvoreBinariaPesquisa) resultado.esquerda;
        }
        return resultado;
    }

    
    
    
    public ArvoreBinariaPesquisa<E> getMaximoRamo(){      
        ArvoreBinariaPesquisa<E> resultado = this;
        while (resultado.direita != null) { // o maior nó será o nó mais a direita, não precisa ser a folha
            resultado = (ArvoreBinariaPesquisa) resultado.direita;
        }
        return resultado;
    }
    /**
     * OK Retorna o nó da árvore com o maior valor.
     *
     * @return A raiz da subárvore contendo o valor máximo
     */
    public ArvoreBinariaPesquisa<E> getMaximo() {
        ArvoreBinariaPesquisa<E> root = getRoot();
      
        ArvoreBinariaPesquisa<E> resultado = root;
        while (resultado.direita != null) { // o maior nó será o nó mais a direita, não precisa ser a folha
            resultado = (ArvoreBinariaPesquisa) resultado.direita;
        }
        return resultado;
    }

    /**
     * OK Retorna o nó sucessor do nó especificado.
     *
     * @param no O nó cujo sucessor desejamos localizar
     * @return O sucessor do no ou {
     * @null}.
     */
    public ArvoreBinariaPesquisa<E> sucessor(ArvoreBinariaPesquisa<E> no) {
        ArvoreBinariaPesquisa<E> root = getRoot();
        
        no = root.pesquisa(no.valor);
        if(no==null){
            return null;
        }
        if (no.direita != null) {
            return ((ArvoreBinariaPesquisa<E>) no.direita).getMinimoRamo();
        }

        ArvoreBinariaPesquisa<E> pai = no.pai;

        while (pai != null && no.equals(pai.direita)) {
            no = pai;
            pai = pai.pai;
        }

        return pai;
    }

    /**
     * OK Retorna o nó predecessor do nó especificado.
     *
     * @param no O nó cujo predecessor desejamos localizar
     * @return O predecessor do nó ou {
     * @null}.
     */
    public ArvoreBinariaPesquisa<E> predecessor(ArvoreBinariaPesquisa<E> no) {
        ArvoreBinariaPesquisa<E> root = getRoot();
        
        no = root.pesquisa(no.valor);
        
        if (no != null && no.esquerda != null) {
            return ((ArvoreBinariaPesquisa<E>) no.esquerda).getMaximoRamo();
        }

        ArvoreBinariaPesquisa<E> pai = no.pai;

        while (pai != null && no.equals(pai.esquerda)) {
            no = pai;
            pai = pai.pai;
        }
        return pai;
    }

    /**
     * Insere um nó contendo o valor especificado na árvore.
     *
     * @param valor O valor armazenado no nó.
     * @return O nó inserido
     */
    public ArvoreBinariaPesquisa<E> insere(E valor) {
        return insere(new ArvoreBinariaPesquisa<>(valor));
    }

    /**
     * Insere um nó na árvore.
     *
     * @param no O nó a ser inserido.
     * @return O nó inserido
     */
    //NOME TROCADO POR QUE O BOGADO COM TODA A INFINITA SABEDORIA DELE DE ENGENHEIRO 
    //CIVIL MANDA FAZER INSERT FORA DA ROOT, AI PRECISA DE UMA AUXILIAR.
    public ArvoreBinariaPesquisa<E> insere(ArvoreBinariaPesquisa<E> no) {
        //adicionado para o trabalho de AVL ( por mau uso dos metodos na mainl) que a inserção sempre procurará a raiz antes de tentar inserir
        ArvoreBinariaPesquisa<E> root = getRoot();
        
        return root.inserir(no);
    }
    
    public ArvoreBinariaPesquisa<E> inserir(ArvoreBinariaPesquisa<E> no) {
        
        if (no.valor.compareTo(this.valor) < 0) {
            if (this.esquerda == null) {

                this.setEsquerda(no);
                no.setPai(this);
                return no;
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.esquerda).inserir(no);
            }
        } else {
            if (this.direita == null) {

                this.setDireita(no);
                no.setPai(this);
                return no;
            } else {
                return ((ArvoreBinariaPesquisa<E>) this.direita).inserir(no);
            }
        }
    }

    
    /**
     * FAZENDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
     * Exclui o nó especificado da árvore. Se a raiz for excluída, retorna a
     * nova raiz.
     *
     * @param no O nó a ser excluído.
     * @return A raiz da árvore
     */
    public ArvoreBinariaPesquisa<E> exclui(ArvoreBinariaPesquisa<E> no) {
        // já recebemos o nó
        no = pesquisa(no.valor);
        ArvoreBinariaPesquisa<E> resultado = no;

        // se o nó não possui filhos
        if (resultado.direita == null && resultado.esquerda == null) {
            if (resultado.pai == null) // é a raiz alone
            {
                no.valor = null;
            } else {
                if (resultado == resultado.pai.direita) {
                    resultado.pai.direita = null;
                } else if (resultado == resultado.pai.esquerda) {
                    resultado.pai.esquerda = null;
                }
            }
        } // o nó possui apenas um filho
        else if (resultado.direita != null && resultado.esquerda == null) // sabemos que não será a raiz vazia
        {
            if (resultado.pai != null) // sabemos que não é a raiz
            {
                ((ArvoreBinariaPesquisa<E>) (resultado.direita)).setPai(resultado.pai); // arrumando o pai do filho
                if (resultado == resultado.pai.direita) {
                    resultado.pai.direita = resultado.direita;
                } else if (resultado == resultado.pai.esquerda) {
                    resultado.pai.esquerda = resultado.direita;
                }
            } else // se o nó não tem pai estamos deletando a raiz
            {
                resultado.valor = resultado.direita.valor;
                resultado.direita = null;
            }
        } else if (resultado.direita == null && resultado.esquerda != null) // sabemos que não será a raiz vazia
        {
            if (resultado.pai != null) {
                ((ArvoreBinariaPesquisa<E>) (resultado.esquerda)).setPai(resultado.pai); // arrumando o pai do filho
                if (resultado == resultado.pai.direita) {
                    resultado.pai.direita = resultado.esquerda;
                } else if (resultado == resultado.pai.esquerda) {
                    resultado.pai.esquerda = resultado.esquerda;
                }
            } else // se o nó não tem pai estamos deletando a raiz
            {
                resultado.valor = resultado.esquerda.valor;
                resultado.esquerda = null;
            }
        } // o nó possui dois filhos
        else if (resultado.direita != null && resultado.esquerda != null) {
            while (resultado.direita != null && resultado.esquerda != null) // tem no máximo um filho
            {
                resultado = sucessor(resultado); // pegando o sucessor do nó
            }
            exclui(resultado);
            no.valor = resultado.valor;

        }
        return this; // retorna sempre a raiz da árvore
    }
}
