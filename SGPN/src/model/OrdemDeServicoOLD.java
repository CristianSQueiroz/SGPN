/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Cristian
 */
public class OrdemDeServicoOLD {
    private String dsProduto;
    private String codSAPPDV;
    private String nmPDV;
    private String nmFantasia;
    private String nmCanal;
    private String dsPlano;
    private int numOS;
    private long dtBOV;
    private String nmMunicipio;
    private int numCircuito;
    private int ddd;
    private int numTerminal;
    private int cPFcNPJ;
    private String nmCliente;
    private String contrato;
    private String situacaoOSBOVDTBaixa;
    private String posto;
    private int matVendedor;
    private String pedidoUnico;
    private String nmGTEContas;
    private String redeTrabalhada;
    private String gRA;
    private String planoDE;
    private String gRam;
    private String estacao;
    private String agendamentos;
    private String ultimoAgendamento;
    private String codMotivoPend;
    private String motivoPendencia;
    private String macroPendencia;
    private String resTratamentoOS;
    private String situacaoOS;
    private String backLog;
    private int dias;
    private long dtPendencia;
    private String motivoCancelamento;
    private String descricaoMotivo;
    private String nmSolicitante;
    private String matriculaDemanda;
    private String dsEndereco;
    private String dsBairro;
    private long tempoAG;
    private String codLocalidade;
    private String codLogradouro;
    private String tipoProduto;
    private String reAgendamento;
    private int dddTerminalFixo;

    public OrdemDeServicoOLD(String dsProduto, String codSAPPDV, String nmPDV, String nmFantasia, String nmCanal, String dsPlano, int numOS, long dtBOV, String nmMunicipio, int numCircuito, int ddd, int numTerminal, int cPFcNPJ, String nmCliente, String contrato, String situacaoOSBOVDTBaixa, String posto, int matVendedor, String pedidoUnico, String nmGTEContas, String redeTrabalhada, String gRA, String planoDE, String gRam, String estacao, String agendamentos, String ultimoAgendamento, String codMotivoPend, String motivoPendencia, String macroPendencia, String resTratamentoOS, String situacaoOS, String backLog, int dias, long dtPendencia, String motivoCancelamento, String descricaoMotivo, String nmSolicitante, String matriculaDemanda, String dsEndereco, String dsBairro, long tempoAG, String codLocalidade, String codLogradouro, String tipoProduto, String reAgendamento, int dddTerminalFixo) {
        this.dsProduto = dsProduto;
        this.codSAPPDV = codSAPPDV;
        this.nmPDV = nmPDV;
        this.nmFantasia = nmFantasia;
        this.nmCanal = nmCanal;
        this.dsPlano = dsPlano;
        this.numOS = numOS;
        this.dtBOV = dtBOV;
        this.nmMunicipio = nmMunicipio;
        this.numCircuito = numCircuito;
        this.ddd = ddd;
        this.numTerminal = numTerminal;
        this.cPFcNPJ = cPFcNPJ;
        this.nmCliente = nmCliente;
        this.contrato = contrato;
        this.situacaoOSBOVDTBaixa = situacaoOSBOVDTBaixa;
        this.posto = posto;
        this.matVendedor = matVendedor;
        this.pedidoUnico = pedidoUnico;
        this.nmGTEContas = nmGTEContas;
        this.redeTrabalhada = redeTrabalhada;
        this.gRA = gRA;
        this.planoDE = planoDE;
        this.gRam = gRam;
        this.estacao = estacao;
        this.agendamentos = agendamentos;
        this.ultimoAgendamento = ultimoAgendamento;
        this.codMotivoPend = codMotivoPend;
        this.motivoPendencia = motivoPendencia;
        this.macroPendencia = macroPendencia;
        this.resTratamentoOS = resTratamentoOS;
        this.situacaoOS = situacaoOS;
        this.backLog = backLog;
        this.dias = dias;
        this.dtPendencia = dtPendencia;
        this.motivoCancelamento = motivoCancelamento;
        this.descricaoMotivo = descricaoMotivo;
        this.nmSolicitante = nmSolicitante;
        this.matriculaDemanda = matriculaDemanda;
        this.dsEndereco = dsEndereco;
        this.dsBairro = dsBairro;
        this.tempoAG = tempoAG;
        this.codLocalidade = codLocalidade;
        this.codLogradouro = codLogradouro;
        this.tipoProduto = tipoProduto;
        this.reAgendamento = reAgendamento;
        this.dddTerminalFixo = dddTerminalFixo;
    }

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String dsProduto) {
        this.dsProduto = dsProduto;
    }

    public String getCodSAPPDV() {
        return codSAPPDV;
    }

    public void setCodSAPPDV(String codSAPPDV) {
        this.codSAPPDV = codSAPPDV;
    }

    public String getNmPDV() {
        return nmPDV;
    }

    public void setNmPDV(String nmPDV) {
        this.nmPDV = nmPDV;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public String getNmCanal() {
        return nmCanal;
    }

    public void setNmCanal(String nmCanal) {
        this.nmCanal = nmCanal;
    }

    public String getDsPlano() {
        return dsPlano;
    }

    public void setDsPlano(String dsPlano) {
        this.dsPlano = dsPlano;
    }

    public int getNumOS() {
        return numOS;
    }

    public void setNumOS(int numOS) {
        this.numOS = numOS;
    }

    public long getDtBOV() {
        return dtBOV;
    }

    public void setDtBOV(long dtBOV) {
        this.dtBOV = dtBOV;
    }

    public String getNmMunicipio() {
        return nmMunicipio;
    }

    public void setNmMunicipio(String nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
    }

    public int getNumCircuito() {
        return numCircuito;
    }

    public void setNumCircuito(int numCircuito) {
        this.numCircuito = numCircuito;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public int getNumTerminal() {
        return numTerminal;
    }

    public void setNumTerminal(int numTerminal) {
        this.numTerminal = numTerminal;
    }

    public int getcPFcNPJ() {
        return cPFcNPJ;
    }

    public void setcPFcNPJ(int cPFcNPJ) {
        this.cPFcNPJ = cPFcNPJ;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getSituacaoOSBOVDTBaixa() {
        return situacaoOSBOVDTBaixa;
    }

    public void setSituacaoOSBOVDTBaixa(String situacaoOSBOVDTBaixa) {
        this.situacaoOSBOVDTBaixa = situacaoOSBOVDTBaixa;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public int getMatVendedor() {
        return matVendedor;
    }

    public void setMatVendedor(int matVendedor) {
        this.matVendedor = matVendedor;
    }

    public String getPedidoUnico() {
        return pedidoUnico;
    }

    public void setPedidoUnico(String pedidoUnico) {
        this.pedidoUnico = pedidoUnico;
    }

    public String getNmGTEContas() {
        return nmGTEContas;
    }

    public void setNmGTEContas(String nmGTEContas) {
        this.nmGTEContas = nmGTEContas;
    }

    public String getRedeTrabalhada() {
        return redeTrabalhada;
    }

    public void setRedeTrabalhada(String redeTrabalhada) {
        this.redeTrabalhada = redeTrabalhada;
    }

    public String getgRA() {
        return gRA;
    }

    public void setgRA(String gRA) {
        this.gRA = gRA;
    }

    public String getPlanoDE() {
        return planoDE;
    }

    public void setPlanoDE(String planoDE) {
        this.planoDE = planoDE;
    }

    public String getgRam() {
        return gRam;
    }

    public void setgRam(String gRam) {
        this.gRam = gRam;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(String agendamentos) {
        this.agendamentos = agendamentos;
    }

    public String getUltimoAgendamento() {
        return ultimoAgendamento;
    }

    public void setUltimoAgendamento(String ultimoAgendamento) {
        this.ultimoAgendamento = ultimoAgendamento;
    }

    public String getCodMotivoPend() {
        return codMotivoPend;
    }

    public void setCodMotivoPend(String codMotivoPend) {
        this.codMotivoPend = codMotivoPend;
    }

    public String getMotivoPendencia() {
        return motivoPendencia;
    }

    public void setMotivoPendencia(String motivoPendencia) {
        this.motivoPendencia = motivoPendencia;
    }

    public String getMacroPendencia() {
        return macroPendencia;
    }

    public void setMacroPendencia(String macroPendencia) {
        this.macroPendencia = macroPendencia;
    }

    public String getResTratamentoOS() {
        return resTratamentoOS;
    }

    public void setResTratamentoOS(String resTratamentoOS) {
        this.resTratamentoOS = resTratamentoOS;
    }

    public String getSituacaoOS() {
        return situacaoOS;
    }

    public void setSituacaoOS(String situacaoOS) {
        this.situacaoOS = situacaoOS;
    }

    public String getBackLog() {
        return backLog;
    }

    public void setBackLog(String backLog) {
        this.backLog = backLog;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public long getDtPendencia() {
        return dtPendencia;
    }

    public void setDtPendencia(long dtPendencia) {
        this.dtPendencia = dtPendencia;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public String getDescricaoMotivo() {
        return descricaoMotivo;
    }

    public void setDescricaoMotivo(String descricaoMotivo) {
        this.descricaoMotivo = descricaoMotivo;
    }

    public String getNmSolicitante() {
        return nmSolicitante;
    }

    public void setNmSolicitante(String nmSolicitante) {
        this.nmSolicitante = nmSolicitante;
    }

    public String getMatriculaDemanda() {
        return matriculaDemanda;
    }

    public void setMatriculaDemanda(String matriculaDemanda) {
        this.matriculaDemanda = matriculaDemanda;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public long getTempoAG() {
        return tempoAG;
    }

    public void setTempoAG(long tempoAG) {
        this.tempoAG = tempoAG;
    }

    public String getCodLocalidade() {
        return codLocalidade;
    }

    public void setCodLocalidade(String codLocalidade) {
        this.codLocalidade = codLocalidade;
    }

    public String getCodLogradouro() {
        return codLogradouro;
    }

    public void setCodLogradouro(String codLogradouro) {
        this.codLogradouro = codLogradouro;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getReAgendamento() {
        return reAgendamento;
    }

    public void setReAgendamento(String reAgendamento) {
        this.reAgendamento = reAgendamento;
    }

    public int getDddTerminalFixo() {
        return dddTerminalFixo;
    }

    public void setDddTerminalFixo(int dddTerminalFixo) {
        this.dddTerminalFixo = dddTerminalFixo;
    }
    
    
    
}
