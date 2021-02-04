package avaliacao2evolutiva.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.uma.jmetal.util.front.impl.ArrayFront;

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.operators.selection.SelectionFactory;
import jmetal.util.Configuration;
import jmetal.util.JMException;


public class SugestionMain {

	public static Logger      logger_ ;      // Logger object
	  public static FileHandler fileHandler_ ; // FileHandler object

	public static void main(String[] args) throws 
    JMException, 
    SecurityException, 
    IOException, 
    ClassNotFoundException{
		 	Problem   problem   ; // The problem to solve
		    Algorithm algorithm ; // The algorithm to use
		    Operator  selection ; // Selection operator
		    
		    HashMap  parameters ; // Operator parameters
		    
		    // Logger object and file to store log messages
		    logger_      = Configuration.logger_ ;
		    fileHandler_ = new FileHandler("NSGAII_main.log"); 
		    logger_.addHandler(fileHandler_) ;
		        
		    problem = new SugestionProblem();
		    
		    algorithm =new NSGAII(problem);

		    // Algorithm parameters
		    algorithm.setInputParameter("populationSize",SugestionProblem.records.size());
		    algorithm.setInputParameter("maxEvaluations",SugestionProblem.records.size());


		    // Selection Operator 
		    parameters = null ;
		    selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;                           

		    algorithm.addOperator("selection",selection);

		    // Execute the Algorithm
		    long initTime = System.currentTimeMillis();
		    SolutionSet population = algorithm.execute();
		    
		    long estimatedTime = System.currentTimeMillis() - initTime;
		    
		    // Result messages 
		    logger_.info("Total execution time: "+estimatedTime + "ms");
		    ((NSGAII)algorithm).population.printVariablesToFile("VAR");    
		    population.printVariablesNamesToFile("NOMES");
		    logger_.info("Total de Avaliações "+SugestionProblem.records.size());
		    
		    
		    LineChartEx ex = new LineChartEx(new ArrayFront("VAR"," ").getMatrix());
		    ex.setVisible(true);
	}

}
