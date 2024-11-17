package arvores;
public class ArvoreRubroNegra extends ArvoreDePesquisa{
    
    @Override
    protected void inOrder(No atual){
        if(hasLeft(atual)) inOrder(leftChild(atual));
        System.out.print(atual.element + "[" + atual.cor + "]    ");
        if(hasRight(atual)) inOrder(rightChild(atual));
    }

    private void rotacaoDuplaDireita(No no){
        rotacaoSimplesEsquerda(leftChild(no));
        rotacaoSimplesDireita(no);
    }
    
    private void rotacaoDuplaEsquerda(No no){
        rotacaoSimplesDireita(rightChild(no));
        rotacaoSimplesEsquerda(no);
    }
    private void rotacaoSimplesEsquerda(No no){
        No filho = rightChild(no);
        if(!isRoot(no)){
            filho.cor = filho.cor == "Rubro" ? "Negro" : "Rubro";
            No pai = parent(no);
            filho.parent = pai;
            if(leftChild(pai) == no) pai.lChild = filho;
            else pai.rChild = filho;
        }
        else{
            filho.parent = null;
            raiz = filho;
            filho.cor = "Negro";
        }
        no.cor = no.cor == "Rubro" ? "Negro" : "Rubro";
        no.rChild = leftChild(filho);
        rightChild(no).parent = no;
        filho.lChild = no;
        no.parent = filho;
    }
    private void rotacaoSimplesDireita(No no){
        No filho = leftChild(no);
        if(!isRoot(no)){
            filho.cor = filho.cor == "Rubro" ? "Negro" : "Rubro";
            No pai = parent(no);
            filho.parent = pai;
            if(leftChild(pai) == no) pai.lChild = filho;
            else pai.rChild = filho;
        }
        else{
            filho.parent = null;
            raiz = filho;
            filho.cor = "Negro";
        }
        no.cor = no.cor == "Rubro" ? "Negro" : "Rubro";
        no.lChild = rightChild(filho);
        leftChild(no).parent = no;
        filho.rChild = nulo;
        no.parent = filho;
    }

    private void repintamento(No no){
        if(isRoot(no)) return;
        No pai = parent(no);
        if(!isRoot(pai)){
            No avo = parent(pai);
            No tio = leftChild(avo) == pai ? rightChild(avo) : leftChild(avo);
            if(no.cor == "Rubro" && pai.cor == "Rubro"){
                if(tio.cor == "Rubro"){
                    pai.cor = "Negro";
                    tio.cor = "Negro";
                    if(!isRoot(avo)) avo.cor = avo.cor == "Rubro" ? "Negro" : "Rubro";
                }
                else{
                    if(pai == leftChild(avo)){
                        if(no == leftChild(pai)) rotacaoSimplesDireita(avo);
                        else rotacaoDuplaDireita(avo);
                    }
                    else{
                        if(no == rightChild(pai)) rotacaoSimplesEsquerda(avo);
                        else rotacaoDuplaEsquerda(avo);
                    }
                }
                repintamento(avo);
            }
        }
    }

    @Override
    public boolean isInternal(No no){
        if(isEmpty()) throw new BaseException("Árvore vazia, não há elementos");
        return no.lChild != nulo || no.rChild != nulo;
    }
    
    @Override
    public boolean isExternal(No no){
        if(isEmpty()) throw new BaseException("Árvore vazia, não há elementos");
        return no.lChild == nulo && no.rChild == nulo;
    }


    @Override
    public boolean hasLeft(No no){
        return no.lChild != nulo;
    }
    
    @Override
    public boolean hasRight(No no){
        return no.rChild != nulo;
    }

    @Override
    public void insert(Object elem){
        No novo = new No(elem, "Rubro");
        if(isEmpty()){
            raiz = novo;
            novo.cor = "Negro";
        }
        else{
            No pai = find(elem, raiz);
            if(pai.element == elem) throw new BaseException("Elemento já existe na Árvore");
            else{
                if((int)elem < (int)pai.element) pai.lChild = novo;
                else pai.rChild = novo;
                novo.parent = pai;
                pai = parent(novo);
                if(pai.cor == "Rubro") repintamento(novo);
            }
        }
        ++n;
    }                                   
}