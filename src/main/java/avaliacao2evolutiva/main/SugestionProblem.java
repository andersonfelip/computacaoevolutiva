package avaliacao2evolutiva.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.variable.BinaryReal;
import jmetal.util.JMException;

public class SugestionProblem extends Problem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static List<PesquisaIbgeNomes> records;
	public static int evaluations = 0;
	
	public SugestionProblem() {
		getVariables();
		numberOfVariables_ = 1;
		numberOfObjectives_ = 4;
		numberOfConstraints_ = 1;
		int    [] precision_ = new int[numberOfVariables_] ;
		problemName_ = "SN1";

		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];

		for (int var = 0; var < numberOfVariables_; var++) {
			lowerLimit_[var] = 0.0;
			upperLimit_[var] = 1.0;
			precision_[var] = 30;
		}
		setPrecision(precision_);
		
		solutionType_ = new SugestionSolutionType(this);
	}
	
	public static void getVariables() {
		records = new ArrayList<PesquisaIbgeNomes>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("nomes.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
				
	    String line;
	    
	    try {
	    	PesquisaIbgeNomes item= null;
	    	int count =0;
			while ((line = br.readLine()) != null) {
				if(count == 0) {
					count++;
					continue;
				}
				if(count == 2) {
					count =0;
					continue;
				}
			    String[] values = line.split(",");
			    
			    item = new PesquisaIbgeNomes();
			    item.setAlternativeNames(values[0]);
			    item.setClassification(values[1]);
			    item.setFirstName(values[2]);
			    item.setFrequencyFemale(values[3].equals("") ? 0 : Integer.parseInt(values[3]));
			    item.setFrequencyMale(values[4].equals("") ? 0 : Integer.parseInt(values[4]));
			    item.setFrequencyTotal(values[5].equals("") ? 0 :Integer.parseInt(values[5]));
			    item.setFrequencyGroup(values[6].equals("") ? 0 :Integer.parseInt(values[6]));
			    item.setGroupName(values[7]);
			    item.setRatio(Double.parseDouble(values[8]));
			    
			    records.add(item);
			    count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void evaluate(Solution solution) throws JMException {
	    Variable[] decisionVariables  = solution.getDecisionVariables();
	    BinaryReal binaryVariable = null;
	    String nome = "";
	    int counterNomeGrande= 0 ;
	    int counterOutrasLetrasDoAlfabeto = 0;
	    int counterNomeFrequente = 0;
	    int counterNomeRaro = 0;
	    for (int i = 0; i < decisionVariables.length; i++) {
	    	binaryVariable = (BinaryReal)decisionVariables[i];
	    	nome = new String (binaryVariable.bits_.toByteArray());
	    	System.out.println(nome);
	    	
	    	if(SugestionProblem.records.get(evaluations).getFrequencyTotal() >= 7000) {
	    		counterNomeFrequente++;
	    	}
	    	if(SugestionProblem.records.get(evaluations).getFrequencyTotal() <= 5000) {
	    		counterNomeRaro++;
	    	}
	    	if(nome.length() >= 6) {
	    		counterNomeGrande++;
	    	}
	    	if(!nome.toUpperCase().startsWith("A") && !nome.toUpperCase().startsWith("B") && !nome.toUpperCase().startsWith("C")){
	    		counterOutrasLetrasDoAlfabeto++;
	    	}
		}
	    solution.setObjective(0, 0.2*counterNomeGrande);            
	    solution.setObjective(1, 0.2*counterOutrasLetrasDoAlfabeto);
	    solution.setObjective(2, 0.3*counterNomeFrequente);
	    solution.setObjective(3, 0.3*counterNomeRaro);
	    binaryVariable.setLowerBound((0.2*(-1*counterNomeGrande)) + (0.2*(-1*counterOutrasLetrasDoAlfabeto)) + (0.3*(-1*counterNomeFrequente)) + (0.3*(-1*counterNomeRaro)));
	    binaryVariable.setUpperBound((0.4*(counterNomeGrande)) + (0.4*(counterOutrasLetrasDoAlfabeto)) + (0.5*(counterNomeFrequente)) + (0.5*(counterNomeRaro)));
	    evaluations++;
	}
	
	@Override
	public void evaluateConstraints(Solution solution) throws JMException {
		
		double total = solution.getOverallConstraintViolation();
		Variable[] decisionVariables  = solution.getDecisionVariables();
	    BinaryReal binaryVariable;
	    String nome = "";
	    int number = 0;
	    String nomeCompleto = ""; 
	    for (int i = 0; i < decisionVariables.length; i++) {
	    	binaryVariable = (BinaryReal)decisionVariables[i];
	    	nome = new String (binaryVariable.bits_.toByteArray());
	    	nomeCompleto = nome + " SANTOS ROCHA";
	    	if(nomeCompleto.length() > 40) {
	    		number++;
	    	}
	    	if(nome.length() < 3) {
	    		number++;
	    	}
	    	
		}
	        
	    solution.setOverallConstraintViolation(total+number);    
	    solution.setNumberOfViolatedConstraint(number); 
	}
	
}
