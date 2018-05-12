package utfpr.dainf.ct.ed.exemplo;

import java.util.Stack;

/**
 * UTFPR - Universidade Tecnológica Federal do Paraná DAINF - Departamento
 * Acadêmico de Informática
 *
 * Exemplo de implementação de árvore AVL.
 *
 * @author Wilson Horstmeyer Bogado <wilson@utfpr.edu.br>
 * @param <E> O tipo do valor armazenado nos nós da árvore
 */
public class ArvoreAVL<E extends Comparable<E>> extends ArvoreBinariaPesquisa<E> {

    protected byte fb;

    /* protected ArvoreAVL<E> direita;
    protected ArvoreAVL<E> esquerda;
    protected ArvoreAVL<E> pai;*/

 /*@Override
    public ArvoreAVL<E> getDireita() {
        return direita;
    }

    public void setDireita(ArvoreAVL<E> direita) {
        this.direita = direita;
    }

    @Override
    public ArvoreAVL<E> getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(ArvoreAVL<E> esquerda) {
        this.esquerda = esquerda;
    }

    @Override
    public ArvoreAVL<E> getPai() {
        return pai;
    }

    public void setPai(ArvoreAVL<E> pai) {
        this.pai = pai;
    }*/
    /**
     * Cria uma árvore AVL com o valor da raiz nulo
     */
    public ArvoreAVL() {
    }

    /**
     * Cria uma árvore AVL cuja raiz armazena o valor especificado.
     *
     * @param valor O valor a ser armazenado
     */
    public ArvoreAVL(E valor) {
        super(valor);
    }

    /**
     * Retorna o fator de balanço deste nó.
     *
     * @return O fator de balanço deste nó
     */
    public byte getFb() {
        return fb;
    }

    /**
     * Inicializa o fator de balaço deste nó.
     *
     * @param fb O fator de balanço a ser atribuído
     */
    protected void setFb(byte fb) {
        this.fb = fb;
    }

    /**
     * Executa a operação de rotação à esquerda em torno do nó especificado.
     *
     * @param x O nó pivo (desregulado)
     * @return O nó que ocupou o lugar de x
     */
    protected ArvoreAVL<E> rotacaoEsquerda(ArvoreAVL<E> x) {
        ArvoreAVL<E> root = (ArvoreAVL<E>) getRoot();
        ArvoreAVL<E> y = (ArvoreAVL) x.direita;

        x.setDireita(y.esquerda);
        y.setPai(x.pai);

        if (x.pai == null) {
            root = y;
        } else {
            if (x == x.pai.esquerda) {
                x.pai.setEsquerda(y);
            } else {
                x.pai.setDireita(y);
            }
        }

        y.setEsquerda(x);

        x.setPai(y);

        return root;
        //throw new RuntimeException("Não implementado");
    }

    /**
     * Executa a operação de rotação à direita em torno do nó especificado.
     *
     * @param y O nó pivo (desregulado)
     * @return O nó que ocupou o lugar de y
     */
    protected ArvoreAVL<E> rotacaoDireita(ArvoreAVL<E> y) {
        ArvoreAVL<E> root = (ArvoreAVL<E>) getRoot();
        ArvoreAVL<E> x = (ArvoreAVL) y.esquerda;

        y.setEsquerda(x.direita);
        x.setPai(y.pai);

        if (y.pai == null) {
            root = x;
        } else {
            if (y == y.pai.esquerda) {
                y.pai.setEsquerda(x);
            } else {
                y.pai.setDireita(x);
            }
        }

        x.setDireita(y);

        y.setPai(x);

        return root;
    }

    /**
     * Executa a rotação para restaurar os fatores de balanço da árvore.
     *
     * @param p O nó desregulado
     * @param q O nó inserido
     * @return A raíz da árvore, possivelmente modificada
     */
    protected ArvoreAVL<E> rotacao(ArvoreAVL<E> p, ArvoreAVL<E> q) {
        ArvoreAVL<E> root = (ArvoreAVL<E>) getRoot();
        ArvoreAVL<E> u = q;

        while (u.pai != p && u.pai != null) {
            u = (ArvoreAVL) u.pai;
        }

        if (p.fb < 0) {
            if (u.fb < 0) {
                root = root.rotacaoDireita(p);
            } else {
                root = root.rotacaoEsquerda((ArvoreAVL) p.esquerda);
                root = root.rotacaoDireita(p);
            }
        } else {
            if (u.fb > 0) {
                root = root.rotacaoEsquerda(p);
            } else {
                root = root.rotacaoDireita((ArvoreAVL) p.direita);
                root = root.rotacaoEsquerda(p);
            }
        }

        return root;
        //throw new RuntimeException("Não implementado");
    }

    //tafazendoafuncaoerrada
    /**
     * Ajusta o fator de balanço após uma inserção. Retorna o primeiro nó
     * desregulado encontrado. Caso não haja nó desregulado, retorna o último nó
     * cujo fator de balanço foi ajustado.
     *
     * @param no O nó inserido
     * @return O primeiro nó desregulado encontrado ou o primeiro regulado
     */
    protected ArvoreAVL<E> ajustaFbInsercao(ArvoreAVL<E> no) {
        ArvoreAVL<E> temp = no;
        while (temp != null) {
            int a = calculaAltura(temp.esquerda);
            int b = calculaAltura(temp.direita);
            temp.setFb((byte) (b - a));
            temp = (ArvoreAVL) temp.pai;
        }

        return this;
        //throw new RuntimeException("Não implementado");
    }

    //tafazendoafuncaoerrada
    /**
     * Ajusta o fator de balanço após uma exclusão. Retorna {@code null} caso
     * não haja nó desregulado.
     *
     * @param no O pai do nó excluído
     * @return O primeiro nó desregulado encontrado ou {@code null}
     */
    protected ArvoreAVL<E> ajustaFbExclusao(ArvoreAVL<E> no) {
        ArvoreAVL<E> temp = (ArvoreAVL<E>) getMinimo();

        while (temp != null) {
            temp.fb = (byte) (calculaAltura(temp.direita) - calculaAltura(temp.esquerda));
            temp = (ArvoreAVL<E>) sucessor(temp);
        }

        return this;
        //throw new RuntimeException("Não implementado");
    }

    /**
     * Insere um nó na árvore AVL. Compatibiliza o tipo de dado com a super
     * classe.
     *
     * @param no O nó a ser inserido
     * @return O nó inserido
     */
    protected ArvoreAVL<E> insere(ArvoreAVL<E> no) {
        super.insere(no);

//super.insere(no);
/*public void a(){//////////////////////////////////////////////////////////////////////////////////////////
        super.insere(no);
    }*/
        ArvoreAVL<E> temp = no;
        ajustaFbInsercao(no);

        while (temp != null) {
            /*int a =calculaAltura(temp.direita) ,b = calculaAltura(temp.esquerda);
            if (Math.abs(a - b) > 1) {
                break;
            }*/
            if (Math.abs(temp.fb) > 1) {
                break;
            }
            temp = (ArvoreAVL) temp.pai;
        }
        //temp é o que está desregulado
        if (temp != null) {
            rotacao(temp, no);
            ajustaFbExclusao(no);
        }

        return no;
        //throw new RuntimeException("Não implementado");
    }

    /**
     * Insere um nó na árvore contendo o valor especificado.
     *
     * @param valor O valor armazenado no nó inserido
     * @return A raiz da árvore, potencialmente modificada
     */
    public ArvoreAVL<E> insere(E valor) {
        return insere(new ArvoreAVL<>(valor));
    }

    /**
     * Exclui um nó de uma árvore AVL.
     *
     * @param no O nó a ser excluído.
     * @return A raiz da árvore, potencialmente modificada
     */
    public ArvoreAVL<E> exclui(ArvoreAVL<E> no) {
        ArvoreAVL<E> root = (ArvoreAVL<E>) getRoot();

        //pesquisa antes da exclusao
        no = (ArvoreAVL) root.pesquisa(no.valor);

        //apagando a root com no maximo 1 filho
        if (root == no && (root.esquerda == null || root.direita == null)) {
            if (root.esquerda != null) {
                ((ArvoreAVL) root.esquerda).pai = null;
                return (ArvoreAVL) root.esquerda;
            } else {
                if (root.direita != null) {
                    ((ArvoreAVL) root.direita).pai = null;
                }
                return (ArvoreAVL) root.direita;
            }
        }

        //situacao 3 - dois filhos
        if (no.esquerda != null && no.direita != null) {
            ArvoreAVL<E> sucessorValido = no;
            do {
                sucessorValido = (ArvoreAVL) sucessor(sucessorValido);
            } while (sucessorValido.direita == null
                    || sucessorValido.esquerda == null);

            E valor = sucessorValido.valor;
            exclui(sucessorValido);
            no.valor = valor;

        } else {
            //situacao 1 ou 2 - com no maximo 1 filho

            boolean isFilhoDaEsquerda = (no.pai.esquerda == no);
            boolean isTemFilhoEsquerda = (no.esquerda != null);

            //situacao 1 - sem filhos
            if (no.esquerda == null && no.direita == null) {
                if (isFilhoDaEsquerda) {
                    no.pai.esquerda = null;
                } else {
                    no.pai.direita = null;
                }
            }//situacao 2 - um filho
            else {
                if (isFilhoDaEsquerda) {
                    if (isTemFilhoEsquerda) {
                        no.pai.esquerda = no.esquerda;
                        ((ArvoreAVL<E>) no.esquerda).pai = no.pai;
                    } else {
                        no.pai.esquerda = no.direita;
                        ((ArvoreAVL<E>) no.direita).pai = no.pai;
                    }
                } else {
                    if (isTemFilhoEsquerda) {
                        no.pai.direita = no.esquerda;
                        ((ArvoreAVL<E>) no.esquerda).pai = no.pai;
                    } else {
                        no.pai.direita = no.direita;
                        ((ArvoreAVL<E>) no.direita).pai = no.pai;
                    }
                }
            }

            //comum para situacoes 1 e 2, balancear a arvore
            no = (ArvoreAVL) no.pai;
            int tempAltura;
            while (no != null) {
                tempAltura = (calculaAltura(no.direita) - calculaAltura(no.esquerda));

                //no desbalanceado
                if (Math.abs(tempAltura) > 1) {
                    if (tempAltura < 0) {
                        root = root.rotacaoDireita(no);
                    } else {
                        root = root.rotacaoEsquerda(no);
                    }
                }

                no = (ArvoreAVL) no.pai;
            }

            ajustaFbExclusao(no);
        }

        return root;
        //throw new RuntimeException("Não implementado");
    }

}
