package sur.softsurena.entidades;

import java.sql.Timestamp;
import lombok.NonNull;

public class Analisis {
    private final int id;
    @NonNull private final int id_doctor;
    @NonNull private final int id_paciente;
    @NonNull private final Timestamp fecha_hora_creada;
    @NonNull private final Timestamp fecha_hora_vista;
    @NonNull private final Boolean T_BHCG;
    @NonNull private final Boolean T_EMB_ORINA;
    @NonNull private final Boolean T_EMB_SANGRE;
    @NonNull private final Boolean T_ANT_AUSTRALIANO_BBSAG;
    @NonNull private final Boolean T_CLAMIDIA_IGA;
    @NonNull private final Boolean T_FTA_ABS;
    @NonNull private final Boolean T_HIV;
    @NonNull private final Boolean T_VDRL;
    @NonNull private final Boolean T_ACIDO_URICO;
    @NonNull private final Boolean T_ANT_FEBRILES;
    @NonNull private final Boolean T_ASO_LATEX;
    @NonNull private final Boolean T_BILIRRUBINA;
    @NonNull private final Boolean T_COLESTEROL_TOTAL;
    @NonNull private final Boolean T_COLESTEROL_HDL;
    @NonNull private final Boolean T_COLESTEROL_HDL_LDL;
    @NonNull private final Boolean T_COPROLOGICO;
    @NonNull private final Boolean T_CREATININA_SUERO;
    @NonNull private final Boolean T_CULTIVO_FARINGE;
    @NonNull private final Boolean T_CULTIVO_HECES;
    @NonNull private final Boolean T_CULTIVO_HERIDA_AEROBICO;
    @NonNull private final Boolean T_CULTIVO_OIDO;
    @NonNull private final Boolean T_CULTIVO_SANGRE;
    @NonNull private final Boolean T_CULTIVO_SEMEN;
    @NonNull private final Boolean T_CULTIVO_URETRA;
    @NonNull private final Boolean T_CULTIVO_VAGINA;
    @NonNull private final Boolean T_ELECTRO_HEMOGLOBINA;
    @NonNull private final Boolean T_ERITROSEDIMENTACION;
    @NonNull private final Boolean T_ESPERMATOGRAMA;
    @NonNull private final Boolean T_ESTRADIOL;
    @NonNull private final Boolean T_ESTROGENO_TOTALES;
    @NonNull private final Boolean T_FACTOR_REUMATOIDE;
    @NonNull private final Boolean T_FALCEMIA;
    @NonNull private final Boolean T_FOSFATASA_ALCALINA;
    @NonNull private final Boolean T_FSH;
    @NonNull private final Boolean T_GLICEMIA;
    @NonNull private final Boolean T_HEMOGLO_GLUCOSILADA;
    @NonNull private final Boolean T_HEMOGRAMA;
    @NonNull private final Boolean T_HEPATITIS_A;
    @NonNull private final Boolean T_HEPATITIS_C;
    @NonNull private final Boolean T_KOH;
    @NonNull private final Boolean T_LH;
    @NonNull private final Boolean T_ORINA;
    @NonNull private final Boolean T_PCR;
    @NonNull private final Boolean T_PROGESTERONA;
    @NonNull private final Boolean T_PROLACTINA;
    @NonNull private final Boolean T_PROTEINAS_TOTALES;
    @NonNull private final Boolean T_PSA_TOTAL;
    @NonNull private final Boolean T_PSA_LIBRE;
    @NonNull private final Boolean T_SANG_OCULT_HECES;
    @NonNull private final Boolean T_SGOT_TGO;
    @NonNull private final Boolean T_SGOT_TGP;
    @NonNull private final Boolean T_T3;
    @NonNull private final Boolean T_T4;
    @NonNull private final Boolean T_T4_LIBRE;
    @NonNull private final Boolean T_COOMBS_DIRECTO;
    @NonNull private final Boolean T_COOMBS_INDIRECTO;
    @NonNull private final Boolean T_TESTOSTERONA;
    @NonNull private final Boolean T_TIPIFICACION_SANGUINEA;
    @NonNull private final Boolean T_TOXOPLAS_IGG;
    @NonNull private final Boolean T_TOXOPLAS_IGM;
    @NonNull private final Boolean T_TRIGLICERIDOS;
    @NonNull private final Boolean T_TSH;
    @NonNull private final Boolean T_TUBERCULINA;
    @NonNull private final Boolean T_UREA;
    private final String OTROS;
    private final String USUARIO;
    private final String ROL;

    public Analisis(int id, int id_doctor, int id_paciente, 
            Timestamp fecha_hora_creada, Timestamp fecha_hora_vista, 
            Boolean T_BHCG, Boolean T_EMB_ORINA, Boolean T_EMB_SANGRE, 
            Boolean T_ANT_AUSTRALIANO_BBSAG, Boolean T_CLAMIDIA_IGA, 
            Boolean T_FTA_ABS, Boolean T_HIV, Boolean T_VDRL, 
            Boolean T_ACIDO_URICO, Boolean T_ANT_FEBRILES, Boolean T_ASO_LATEX, 
            Boolean T_BILIRRUBINA, Boolean T_COLESTEROL_TOTAL, 
            Boolean T_COLESTEROL_HDL, Boolean T_COLESTEROL_HDL_LDL, 
            Boolean T_COPROLOGICO, Boolean T_CREATININA_SUERO, 
            Boolean T_CULTIVO_FARINGE, Boolean T_CULTIVO_HECES, 
            Boolean T_CULTIVO_HERIDA_AEROBICO, Boolean T_CULTIVO_OIDO, 
            Boolean T_CULTIVO_SANGRE, Boolean T_CULTIVO_SEMEN, 
            Boolean T_CULTIVO_URETRA, Boolean T_CULTIVO_VAGINA, 
            Boolean T_ELECTRO_HEMOGLOBINA, Boolean T_ERITROSEDIMENTACION, 
            Boolean T_ESPERMATOGRAMA, Boolean T_ESTRADIOL, 
            Boolean T_ESTROGENO_TOTALES, Boolean T_FACTOR_REUMATOIDE, 
            Boolean T_FALCEMIA, Boolean T_FOSFATASA_ALCALINA, 
            Boolean T_FSH, Boolean T_GLICEMIA, Boolean T_HEMOGLO_GLUCOSILADA, 
            Boolean T_HEMOGRAMA, Boolean T_HEPATITIS_A, Boolean T_HEPATITIS_C, 
            Boolean T_KOH, Boolean T_LH, Boolean T_ORINA, Boolean T_PCR, 
            Boolean T_PROGESTERONA, Boolean T_PROLACTINA, 
            Boolean T_PROTEINAS_TOTALES, Boolean T_PSA_TOTAL, 
            Boolean T_PSA_LIBRE, Boolean T_SANG_OCULT_HECES, 
            Boolean T_SGOT_TGO, Boolean T_SGOT_TGP, Boolean T_T3, 
            Boolean T_T4, Boolean T_T4_LIBRE, Boolean T_COOMBS_DIRECTO, 
            Boolean T_COOMBS_INDIRECTO, Boolean T_TESTOSTERONA, 
            Boolean T_TIPIFICACION_SANGUINEA, Boolean T_TOXOPLAS_IGG, 
            Boolean T_TOXOPLAS_IGM, Boolean T_TRIGLICERIDOS, Boolean T_TSH, 
            Boolean T_TUBERCULINA, Boolean T_UREA, String OTROS, String USUARIO, 
            String ROL) {
        this.id = id;
        this.id_doctor = id_doctor;
        this.id_paciente = id_paciente;
        this.fecha_hora_creada = fecha_hora_creada;
        this.fecha_hora_vista = fecha_hora_vista;
        this.T_BHCG = T_BHCG;
        this.T_EMB_ORINA = T_EMB_ORINA;
        this.T_EMB_SANGRE = T_EMB_SANGRE;
        this.T_ANT_AUSTRALIANO_BBSAG = T_ANT_AUSTRALIANO_BBSAG;
        this.T_CLAMIDIA_IGA = T_CLAMIDIA_IGA;
        this.T_FTA_ABS = T_FTA_ABS;
        this.T_HIV = T_HIV;
        this.T_VDRL = T_VDRL;
        this.T_ACIDO_URICO = T_ACIDO_URICO;
        this.T_ANT_FEBRILES = T_ANT_FEBRILES;
        this.T_ASO_LATEX = T_ASO_LATEX;
        this.T_BILIRRUBINA = T_BILIRRUBINA;
        this.T_COLESTEROL_TOTAL = T_COLESTEROL_TOTAL;
        this.T_COLESTEROL_HDL = T_COLESTEROL_HDL;
        this.T_COLESTEROL_HDL_LDL = T_COLESTEROL_HDL_LDL;
        this.T_COPROLOGICO = T_COPROLOGICO;
        this.T_CREATININA_SUERO = T_CREATININA_SUERO;
        this.T_CULTIVO_FARINGE = T_CULTIVO_FARINGE;
        this.T_CULTIVO_HECES = T_CULTIVO_HECES;
        this.T_CULTIVO_HERIDA_AEROBICO = T_CULTIVO_HERIDA_AEROBICO;
        this.T_CULTIVO_OIDO = T_CULTIVO_OIDO;
        this.T_CULTIVO_SANGRE = T_CULTIVO_SANGRE;
        this.T_CULTIVO_SEMEN = T_CULTIVO_SEMEN;
        this.T_CULTIVO_URETRA = T_CULTIVO_URETRA;
        this.T_CULTIVO_VAGINA = T_CULTIVO_VAGINA;
        this.T_ELECTRO_HEMOGLOBINA = T_ELECTRO_HEMOGLOBINA;
        this.T_ERITROSEDIMENTACION = T_ERITROSEDIMENTACION;
        this.T_ESPERMATOGRAMA = T_ESPERMATOGRAMA;
        this.T_ESTRADIOL = T_ESTRADIOL;
        this.T_ESTROGENO_TOTALES = T_ESTROGENO_TOTALES;
        this.T_FACTOR_REUMATOIDE = T_FACTOR_REUMATOIDE;
        this.T_FALCEMIA = T_FALCEMIA;
        this.T_FOSFATASA_ALCALINA = T_FOSFATASA_ALCALINA;
        this.T_FSH = T_FSH;
        this.T_GLICEMIA = T_GLICEMIA;
        this.T_HEMOGLO_GLUCOSILADA = T_HEMOGLO_GLUCOSILADA;
        this.T_HEMOGRAMA = T_HEMOGRAMA;
        this.T_HEPATITIS_A = T_HEPATITIS_A;
        this.T_HEPATITIS_C = T_HEPATITIS_C;
        this.T_KOH = T_KOH;
        this.T_LH = T_LH;
        this.T_ORINA = T_ORINA;
        this.T_PCR = T_PCR;
        this.T_PROGESTERONA = T_PROGESTERONA;
        this.T_PROLACTINA = T_PROLACTINA;
        this.T_PROTEINAS_TOTALES = T_PROTEINAS_TOTALES;
        this.T_PSA_TOTAL = T_PSA_TOTAL;
        this.T_PSA_LIBRE = T_PSA_LIBRE;
        this.T_SANG_OCULT_HECES = T_SANG_OCULT_HECES;
        this.T_SGOT_TGO = T_SGOT_TGO;
        this.T_SGOT_TGP = T_SGOT_TGP;
        this.T_T3 = T_T3;
        this.T_T4 = T_T4;
        this.T_T4_LIBRE = T_T4_LIBRE;
        this.T_COOMBS_DIRECTO = T_COOMBS_DIRECTO;
        this.T_COOMBS_INDIRECTO = T_COOMBS_INDIRECTO;
        this.T_TESTOSTERONA = T_TESTOSTERONA;
        this.T_TIPIFICACION_SANGUINEA = T_TIPIFICACION_SANGUINEA;
        this.T_TOXOPLAS_IGG = T_TOXOPLAS_IGG;
        this.T_TOXOPLAS_IGM = T_TOXOPLAS_IGM;
        this.T_TRIGLICERIDOS = T_TRIGLICERIDOS;
        this.T_TSH = T_TSH;
        this.T_TUBERCULINA = T_TUBERCULINA;
        this.T_UREA = T_UREA;
        this.OTROS = OTROS;
        this.USUARIO = USUARIO;
        this.ROL = ROL;
    }

    public int getId() {
        return id;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public Timestamp getFecha_hora_creada() {
        return fecha_hora_creada;
    }

    public Timestamp getFecha_hora_vista() {
        return fecha_hora_vista;
    }

    public Boolean getT_BHCG() {
        return T_BHCG;
    }

    public Boolean getT_EMB_ORINA() {
        return T_EMB_ORINA;
    }

    public Boolean getT_EMB_SANGRE() {
        return T_EMB_SANGRE;
    }

    public Boolean getT_ANT_AUSTRALIANO_BBSAG() {
        return T_ANT_AUSTRALIANO_BBSAG;
    }

    public Boolean getT_CLAMIDIA_IGA() {
        return T_CLAMIDIA_IGA;
    }

    public Boolean getT_FTA_ABS() {
        return T_FTA_ABS;
    }

    public Boolean getT_HIV() {
        return T_HIV;
    }

    public Boolean getT_VDRL() {
        return T_VDRL;
    }

    public Boolean getT_ACIDO_URICO() {
        return T_ACIDO_URICO;
    }

    public Boolean getT_ANT_FEBRILES() {
        return T_ANT_FEBRILES;
    }

    public Boolean getT_ASO_LATEX() {
        return T_ASO_LATEX;
    }

    public Boolean getT_BILIRRUBINA() {
        return T_BILIRRUBINA;
    }

    public Boolean getT_COLESTEROL_TOTAL() {
        return T_COLESTEROL_TOTAL;
    }

    public Boolean getT_COLESTEROL_HDL() {
        return T_COLESTEROL_HDL;
    }

    public Boolean getT_COLESTEROL_HDL_LDL() {
        return T_COLESTEROL_HDL_LDL;
    }

    public Boolean getT_COPROLOGICO() {
        return T_COPROLOGICO;
    }

    public Boolean getT_CREATININA_SUERO() {
        return T_CREATININA_SUERO;
    }

    public Boolean getT_CULTIVO_FARINGE() {
        return T_CULTIVO_FARINGE;
    }

    public Boolean getT_CULTIVO_HECES() {
        return T_CULTIVO_HECES;
    }

    public Boolean getT_CULTIVO_HERIDA_AEROBICO() {
        return T_CULTIVO_HERIDA_AEROBICO;
    }

    public Boolean getT_CULTIVO_OIDO() {
        return T_CULTIVO_OIDO;
    }

    public Boolean getT_CULTIVO_SANGRE() {
        return T_CULTIVO_SANGRE;
    }

    public Boolean getT_CULTIVO_SEMEN() {
        return T_CULTIVO_SEMEN;
    }

    public Boolean getT_CULTIVO_URETRA() {
        return T_CULTIVO_URETRA;
    }

    public Boolean getT_CULTIVO_VAGINA() {
        return T_CULTIVO_VAGINA;
    }

    public Boolean getT_ELECTRO_HEMOGLOBINA() {
        return T_ELECTRO_HEMOGLOBINA;
    }

    public Boolean getT_ERITROSEDIMENTACION() {
        return T_ERITROSEDIMENTACION;
    }

    public Boolean getT_ESPERMATOGRAMA() {
        return T_ESPERMATOGRAMA;
    }

    public Boolean getT_ESTRADIOL() {
        return T_ESTRADIOL;
    }

    public Boolean getT_ESTROGENO_TOTALES() {
        return T_ESTROGENO_TOTALES;
    }

    public Boolean getT_FACTOR_REUMATOIDE() {
        return T_FACTOR_REUMATOIDE;
    }

    public Boolean getT_FALCEMIA() {
        return T_FALCEMIA;
    }

    public Boolean getT_FOSFATASA_ALCALINA() {
        return T_FOSFATASA_ALCALINA;
    }

    public Boolean getT_FSH() {
        return T_FSH;
    }

    public Boolean getT_GLICEMIA() {
        return T_GLICEMIA;
    }

    public Boolean getT_HEMOGLO_GLUCOSILADA() {
        return T_HEMOGLO_GLUCOSILADA;
    }

    public Boolean getT_HEMOGRAMA() {
        return T_HEMOGRAMA;
    }

    public Boolean getT_HEPATITIS_A() {
        return T_HEPATITIS_A;
    }

    public Boolean getT_HEPATITIS_C() {
        return T_HEPATITIS_C;
    }

    public Boolean getT_KOH() {
        return T_KOH;
    }

    public Boolean getT_LH() {
        return T_LH;
    }

    public Boolean getT_ORINA() {
        return T_ORINA;
    }

    public Boolean getT_PCR() {
        return T_PCR;
    }

    public Boolean getT_PROGESTERONA() {
        return T_PROGESTERONA;
    }

    public Boolean getT_PROLACTINA() {
        return T_PROLACTINA;
    }

    public Boolean getT_PROTEINAS_TOTALES() {
        return T_PROTEINAS_TOTALES;
    }

    public Boolean getT_PSA_TOTAL() {
        return T_PSA_TOTAL;
    }

    public Boolean getT_PSA_LIBRE() {
        return T_PSA_LIBRE;
    }

    public Boolean getT_SANG_OCULT_HECES() {
        return T_SANG_OCULT_HECES;
    }

    public Boolean getT_SGOT_TGO() {
        return T_SGOT_TGO;
    }

    public Boolean getT_SGOT_TGP() {
        return T_SGOT_TGP;
    }

    public Boolean getT_T3() {
        return T_T3;
    }

    public Boolean getT_T4() {
        return T_T4;
    }

    public Boolean getT_T4_LIBRE() {
        return T_T4_LIBRE;
    }

    public Boolean getT_COOMBS_DIRECTO() {
        return T_COOMBS_DIRECTO;
    }

    public Boolean getT_COOMBS_INDIRECTO() {
        return T_COOMBS_INDIRECTO;
    }

    public Boolean getT_TESTOSTERONA() {
        return T_TESTOSTERONA;
    }

    public Boolean getT_TIPIFICACION_SANGUINEA() {
        return T_TIPIFICACION_SANGUINEA;
    }

    public Boolean getT_TOXOPLAS_IGG() {
        return T_TOXOPLAS_IGG;
    }

    public Boolean getT_TOXOPLAS_IGM() {
        return T_TOXOPLAS_IGM;
    }

    public Boolean getT_TRIGLICERIDOS() {
        return T_TRIGLICERIDOS;
    }

    public Boolean getT_TSH() {
        return T_TSH;
    }

    public Boolean getT_TUBERCULINA() {
        return T_TUBERCULINA;
    }

    public Boolean getT_UREA() {
        return T_UREA;
    }

    public String getOTROS() {
        return OTROS;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public String getROL() {
        return ROL;
    }
    
    
    
    
}
