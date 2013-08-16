package br.com.model;

public class AreaConhecimento {

    private int id;
    private String area;

    public AreaConhecimento(int id, String area) {
        this.id = id;
        this.area = area;
    }

    public AreaConhecimento(String area) {
        this.area = area;
    }

    public AreaConhecimento() {
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

    public void setId(String id) {
        try {
            int temp = Integer.parseInt(id);
            this.id = temp;
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSÍVEL FAZER A CONVERSÃO DO ID DA ÁREA DE CONHECIMENTO");
        }
    }

    /**
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

}
