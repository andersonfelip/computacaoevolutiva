package avaliacao2evolutiva.main;

public class PesquisaIbgeNomes {
	private String alternativeNames;
	private String classification;
	private String firstName;
	private int frequencyFemale;
	private int frequencyMale;
	private int frequencyTotal;
	private int frequencyGroup;
	private String groupName;
	private double ratio;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getFrequencyFemale() {
		return frequencyFemale;
	}
	public void setFrequencyFemale(int frequencyFemale) {
		this.frequencyFemale = frequencyFemale;
	}
	public int getFrequencyMale() {
		return frequencyMale;
	}
	public void setFrequencyMale(int frequencyMale) {
		this.frequencyMale = frequencyMale;
	}
	public int getFrequencyTotal() {
		return frequencyTotal;
	}
	public void setFrequencyTotal(int frequencyTotal) {
		this.frequencyTotal = frequencyTotal;
	}
	public int getFrequencyGroup() {
		return frequencyGroup;
	}
	public void setFrequencyGroup(int frequencyGroup) {
		this.frequencyGroup = frequencyGroup;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	public String getAlternativeNames() {
		return alternativeNames;
	}
	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}
	@Override
	public String toString() {
		return "PesquisaIbgeNomes [alternativeNames=" + alternativeNames + ", classification=" + classification
				+ ", firstName=" + firstName + ", frequencyFemale=" + frequencyFemale + ", frequencyMale="
				+ frequencyMale + ", frequencyTotal=" + frequencyTotal + ", frequencyGroup=" + frequencyGroup
				+ ", groupName=" + groupName + ", ratio=" + ratio + "]";
	}
	
	
}
