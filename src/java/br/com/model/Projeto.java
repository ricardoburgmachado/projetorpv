package br.com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projeto {

    private int id;
    private String titulo;
    private String palavrasChave;
    private String resumo;
    private TipoProjeto tipoProjeto;
    private List<Usuario> participantes = new ArrayList<>();
    private ArrayList<String> participantesString = new ArrayList<>();
    private ArrayList<Aluno> participantesAluno = new ArrayList<>();
    private ArrayList<Professor> participantesProfessor = new ArrayList<>();
    private ArrayList<Externo> participantesExterno = new ArrayList<>();
    private Professor professor;
    private boolean arquivo;
    private ArrayList<Custo> custos = new ArrayList<>();
    private AreaConhecimento areaConhecimento;
    private Campus campus;
    private boolean sigilo;
    private StatusProjeto status;
    private Arquivo respaldo;
    private Arquivo prestacaoConta;
    private Date inicio;
    private Date fim;
    private List<Recado> recados;

    public Projeto() {

        this.recados = new ArrayList<>();
        this.status = StatusProjeto.CRIADO;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the palavrasChave
     */
    public String getPalavrasChave() {
        return palavrasChave;
    }

    /**
     * @param palavrasChave the palavrasChave to set
     */
    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    /**
     * @return the resumo
     */
    public String getResumo() {
        return resumo;
    }

    /**
     * @param resumo the resumo to set
     */
    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    /**
     * @return the tipoProjeto
     */
    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    /**
     * @param tipoProjeto the tipoProjeto to set
     */
    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }

    /**
     * @return the participantes
     */
    public List<Usuario> getParticipantes() {
        return participantes;
    }

    /**
     * @param participantes the participantes to set
     */
    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    /**
     * @return the professor
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * @param professor the professor to set
     */
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
                            
    public void setCustos(int idProj, TipoCusto tipoC, String[] custoVal, String[] custoDesc) {
        for (int i = 0; i < custoVal.length; i++) {
            if (valorValido(custoVal[i]) && descricaoValida(custoDesc[i])) {
                this.custos.add(new Custo(idProj, tipoC, custoDesc[i], Double.parseDouble(custoVal[i])));
                //System.out.println("CUSTEIO: idPROJ: "+idProj+" | desc: "+custeioDesc[i]+" valor: "+custeioVal[i]);
            }
        }
    }

    public ArrayList<Custo> getCustos() {
        return this.custos;
    }

    public boolean valorValido(String valor) {
        try {
            double temp = Double.parseDouble(valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean descricaoValida(String desc) {
        if (!desc.isEmpty() && desc != "") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the areaConhecimento
     */
    public AreaConhecimento getAreaConhecimento() {
        return areaConhecimento;
    }

    /**
     * @param areaConhecimento the areaConhecimento to set
     */
    public void setAreaConhecimento(AreaConhecimento areaConhecimento) {
        this.areaConhecimento = areaConhecimento;
    }

    /**
     * @return the campus
     */
    public Campus getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    /**
     * @return the sigilo
     */
    public boolean getSigilo() {
        return sigilo;
    }

    /**
     * @param sigilo the sigilo to set
     */
    public void setSigilo(boolean sigilo) {
        this.sigilo = sigilo;
    }

    /**
     * @return the participantesAluno
     */
    public ArrayList<Aluno> getParticipantesAluno() {
        return participantesAluno;
    }

    /**
     * @param participantesAluno the participantesAluno to set
     */
    public void setParticipantesAluno(ArrayList<Aluno> participantesAluno) {
        this.participantesAluno = participantesAluno;
    }

    /**
     * @return the participantesProfessor
     */
    public ArrayList<Professor> getParticipantesProfessor() {
        return participantesProfessor;
    }

    /**
     * @param participantesProfessor the participantesProfessor to set
     */
    public void setParticipantesProfessor(ArrayList<Professor> participantesProfessor) {
        this.participantesProfessor = participantesProfessor;
    }

    /**
     * @return the participantesExterno
     */
    public ArrayList<Externo> getParticipantesExterno() {
        return participantesExterno;
    }

    /**
     * @param participantesExterno the participantesExterno to set
     */
    public void setParticipantesExterno(ArrayList<Externo> participantesExterno) {
        this.participantesExterno = participantesExterno;
    }

    /**
     * @return the isArquivo
     */
    public boolean getArquivo() {
        return arquivo;
    }

    /**
     * @param isArquivo the isArquivo to set
     */
    public void setArquivo(boolean isArquivo) {
        this.arquivo = isArquivo;
    }

    /**
     * @return the participantesString
     */
    public ArrayList<String> getParticipantesString() {
        return participantesString;
    }

    /**
     * @param participantesString the participantesString to set
     */
    public void setParticipantesString(String[] p) {

        for (int i = 0; i < p.length; i++) {
            this.participantesString.add(p[i].toString());
        }
    }

    public void setCustos(ArrayList<Custo> custos) {
        this.custos = custos;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }
    
    public List<Recado> getRecados() {
        return recados;
    }

    public void setRecados(List<Recado> recados) {
        this.recados = recados;
    }

    public Recado addRecado(String conteudo, Usuario remetente, Date dataEnvio) {

        Recado recado = new Recado(conteudo, remetente, dataEnvio);
        this.recados.add(recado);
        return recado;
    }

    public void removeRecado(Recado recado) {

        if (recado != null) {

            this.recados.remove(recado);
        }

    }

    @Override
    public String toString() {
        return "Projeto{" + "id=" + id + ", titulo=" + titulo + ", palavrasChave=" + palavrasChave + ", resumo=" + resumo + ", tipoProjeto=" + tipoProjeto + ", participantes=" + participantes + ", participantesString=" + participantesString + ", participantesAluno=" + participantesAluno + ", participantesProfessor=" + participantesProfessor + ", participantesExterno=" + participantesExterno + ", professor=" + professor + ", arquivo=" + arquivo + ", custos=" + custos + ", areaConhecimento=" + areaConhecimento + ", campus=" + campus + ", sigilo=" + sigilo + '}';
    }

    /**
     * @return the respaldo
     */
    public Arquivo getRespaldo() {
        return respaldo;
    }

    /**
     * @param respaldo the respaldo to set
     */
    public void setRespaldo(Arquivo respaldo) {
        this.respaldo = respaldo;
    }

    /**
     * @return the prestacao_conta
     */
    public Arquivo getPrestacaoConta() {
        return prestacaoConta;
    }

    /**
     * @param prestacao_conta the prestacao_conta to set
     */
    public void setPrestacaoConta(Arquivo prestacao_conta) {
        this.prestacaoConta = prestacao_conta;
    }
    
    /**
     * @return the inicio
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fim
     */
    public Date getFim() {
        return fim;
    }

    /**
     * @param fim the fim to set
     */
    public void setFim(Date fim) {
        this.fim = fim;
    }
    
    public boolean isExecutando(){
    
        //after = depois
        //before = antes
        Date dataAtual = new Date();
        //System.out.println("************** DATA ATUAL: "+dataAtual);
        //if((dataAtual.after(inicio) && dataAtual.before(fim)) && status.equals("HOMOLOGADO") ){
        if((dataAtual.after(inicio) && dataAtual.before(fim)) && StatusProjeto.HOMOLOGADO.equals(status) ){
            return true;
        }else{        
            return false;
        } 
    }
}
