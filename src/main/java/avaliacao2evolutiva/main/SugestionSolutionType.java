package avaliacao2evolutiva.main;

import java.io.UnsupportedEncodingException;
import java.util.BitSet;

import jmetal.core.Problem;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.variable.BinaryReal;

public class SugestionSolutionType extends BinaryRealSolutionType {

	public static int counter = 0;

	public SugestionSolutionType(Problem problem) {
		super(problem);
	}

	@Override
	public Variable[] createVariables() {
		Variable[] variables = new Variable[1];

		BinaryReal binary = null;
		byte[] bytes = null;
		try {
			bytes = SugestionProblem.records.get(counter).getFirstName().getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		binary = new BinaryReal(BitSet.valueOf(bytes), 8, problem_.getLowerLimit(0),
				problem_.getUpperLimit(0));
		variables[0] = binary;
		counter++;
		return variables;
	}
}
