package arvores;
public class ArvoreRubroNegra extends ArvoreDePesquisa{
    
    private void caso1(No no){

    }

    private 

    private void situacao3(No no){
        No pai = parent(no);
        No irmao = no == leftChild(pai) ? rightChild(pai) : leftChild(pai);
        if(irmao.cor == "Rubro" && pai.cor == "Negro") caso1(pai);
        else{
            No sobrinhoPerto = no == leftChild(pai) ? leftChild(irmao) : rightChild(irmao);
            No sobrinhoLonge = no == leftChild(pai) ? rightChild(irmao) : leftChild(irmao);
            if(irmao.cor == "Negro" && sobrinhoLonge.cor == "Negro" && sobrinhoPerto.cor == "Negro"){ // casos 2
                if(pai.cor == "Rubro") pai.cor = "Negro"; // caso 2B
                irmao.cor = "Rubro"; // ambos casos 2 o irmão vira rubro
            }
        }
    }

    private void situacao4(No no){
        
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
                    if(retorno.cor == "Negro" && filhoEsquerdo.cor == "Rubro") filhoEsquerdo.cor = "Negro";
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
                    if(retorno.cor == "Negro" && filhoDireito.cor == "Rubro") filhoDireito.cor = "Negro";
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
                if(substituto.cor == "Negro" && filhoDireito.cor == "Rubro") filhoDireito.cor = "Negro";
                else if(substituto.cor == "Negro" && filhoDireito.cor == "Negro") situacao3(substituto);
                filhoDireito.parent = pai;
                if(leftChild(pai).element == retorno.element) pai.lChild = filhoDireito;
                else pai.rChild = filhoDireito;
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