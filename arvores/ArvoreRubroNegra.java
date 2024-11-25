package arvores;
public class ArvoreRubroNegra extends ArvoreDePesquisa{
    
    private void caso1(No no, No sucessor){
        if(leftChild(no).cor == "Rubro") rotacaoSimplesDireita(no);
        else rotacaoSimplesEsquerda(no);
        parent(no).cor = "Negro";
        no.cor = "Rubro";
        if(sucessor.element == leftChild(no).element) caso2B(leftChild(no));
        else caso2B(rightChild(no));
    }

    private void caso2B(No no){
        No pai = parent(no);
        No irmao = no == leftChild(pai) ? rightChild(pai) : leftChild(pai);
        pai.cor = "Negro";
        irmao.cor = "Rubro";
        pai.duploNegro = 0;
    }

    private void caso4(No no, No sucessor){
        String corNo = no.cor;
        No pai = parent(no);
        if(sucessor == leftChild(no)){
            rotacaoSimplesEsquerda(no);
            rightChild(pai).cor = "Negro";
        }
        else{
            rotacaoSimplesDireita(no);
            leftChild(pai).cor = "Negro";
        }
        pai.cor = corNo;
        parent(sucessor).cor = "Negro";
    }

    private void situacao3(No no){
        No pai = parent(no);
        No irmao = no == leftChild(pai) ? rightChild(pai) : leftChild(pai);
        if(irmao.cor == "Rubro" && pai.cor == "Negro") caso1(pai, no); // caso 1
        else{
            No sobrinhoPerto = no == leftChild(pai) ? leftChild(irmao) : rightChild(irmao);
            No sobrinhoLonge = no == leftChild(pai) ? rightChild(irmao) : leftChild(irmao);
            if(irmao.cor == "Negro"){
                if(sobrinhoLonge.cor == "Negro" && sobrinhoPerto.cor == "Negro"){
                    if(pai.cor == "Rubro") caso2B(no); // caso 2B
                    else{ // caso 2A
                        irmao.cor = "Rubro";
                        pai.duploNegro = 0;
                        if(!isRoot(pai)){
                            No avo = parent(pai);
                            avo.duploNegro = pai == leftChild(avo) ? 1 : -1;
                        }
                    }
                }
                else if(sobrinhoLonge.cor == "Negro" && sobrinhoPerto.cor == "Rubro"){ // caso 3
                    if(sobrinhoPerto == leftChild(irmao)) rotacaoSimplesDireita(irmao);
                    else rotacaoSimplesEsquerda(irmao);
                    irmao.cor = "Rubro";
                    sobrinhoPerto.cor = "Negro";
                    caso4(pai, no);
                }
                else if(sobrinhoLonge.cor == "Rubro") caso4(pai, no);
            }
        }
    }

    private void situacao4(No no){
        no.cor = "Rubro";
        situacao3(no);
    }

    @Override
    protected void inOrder(No atual){
        if(hasLeft(atual)) inOrder(leftChild(atual));
        System.out.print(atual.element + "[" + atual.cor + (atual.duploNegro != 0 ? " -> Duplo negro: " + atual.duploNegro : "") + "]   ");
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
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        return no.lChild != nulo || no.rChild != nulo;
    }
    
    @Override
    public boolean isExternal(No no){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
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
            if(pai.element == elem) throw new BaseException("Elemento ja existe na arvore");
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

    @Override
    public No remove(Object elem){
        if(isEmpty()) throw new BaseException("arvore vazia, nao ha elementos");
        No no = find(elem, raiz);
        No retorno = no;
        if(no.element != elem) throw new BaseException("Elemento nao existe na arvore");
        boolean ehRaiz = isRoot(no);
        No pai = null;
        if(!ehRaiz) pai = parent(no);
        if(isExternal(no)){
            if(ehRaiz) raiz = null;
            else{
                No esquerdo = leftChild(pai);
                if(esquerdo == no){
                    pai.lChild = nulo;
                    if(retorno.cor == "Negro") pai.duploNegro = 1;
                }
                else{
                    pai.rChild = nulo;
                    if(retorno.cor == "Negro") pai.duploNegro = -1;
                }
            }
        }
        else if(hasLeft(no) && !hasRight(no) || !hasLeft(no) && hasRight(no)){
            if(hasLeft(no)){
                if(ehRaiz){
                    raiz = leftChild(no);
                    raiz.parent = null;
                    raiz.cor = "Negro";
                }
                else{
                    No filhoEsquerdo = leftChild(no);
                    filhoEsquerdo.parent = pai;
                    if(leftChild(pai) == no) pai.lChild = filhoEsquerdo;
                    else pai.rChild = filhoEsquerdo;
                    if(retorno.cor == "Negro"){
                        if(filhoEsquerdo.cor == "Rubro") filhoEsquerdo.cor = "Negro";
                        else situacao3(filhoEsquerdo);
                    }
                    else situacao4(filhoEsquerdo);
                }
            }
            else{
                if(ehRaiz){
                    raiz = rightChild(no);
                    raiz.parent = null;
                    raiz.cor = "Negro";
                }
                else{
                    No filhoDireito = rightChild(no);
                    filhoDireito.parent = pai;
                    if(leftChild(pai) == no) pai.lChild = filhoDireito;
                    else pai.rChild = filhoDireito;
                    if(retorno.cor == "Negro"){
                        if(filhoDireito.cor == "Rubro") filhoDireito.cor = "Negro";
                        else situacao3(filhoDireito);
                    }
                    else situacao4(filhoDireito);
                }
            }
        }
        else{
            No substituto = rightChild(no);
            while(hasLeft(substituto)) substituto = leftChild(substituto);
            no.element = substituto.element;
            pai = parent(substituto);
            if(hasRight(substituto)){
                No filhoDireito = rightChild(substituto);
                filhoDireito.parent = pai;
                if(leftChild(pai).element == retorno.element) pai.lChild = filhoDireito;
                else pai.rChild = filhoDireito;
                if(substituto.cor == "Negro"){
                    if(filhoDireito.cor == "Rubro") filhoDireito.cor = "Negro";
                    else situacao3(filhoDireito);
                }
                else situacao4(filhoDireito);
            }
            else{
                if(substituto == leftChild(pai)){
                    pai.lChild = nulo;
                    if(substituto.cor == "Negro") pai.duploNegro = 1;
                }
                else{
                    pai.rChild = nulo;
                    if(substituto.cor == "Negro") pai.duploNegro = -1;
                }

            }
        }
        --n;
        return retorno;
    }
}