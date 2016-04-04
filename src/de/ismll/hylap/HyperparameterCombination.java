package de.ismll.hylap;

import java.util.Arrays;

import de.ismll.core.Instance;

public class HyperparameterCombination
{
	/**
	 * MAX is excluding. This mean if you want to have indices 0, 1, 2 then MAX=3.
	 */
	public static int HYPERPARAMETER_INDEX_RANGE_MAX = 100;
	
	/**
	 * MAX is excluding. This mean if you want to have indices 0, 1, 2 then MAX=3.
	 */
	public static int HYPERPARAMETER_INDICATOR_RANGE_MAX = 0;
	
	/**
	 * MAX is excluding. This mean if you want to have indices 0, 1, 2 then MAX=3.
	 */
	public static int HYPERPARAMETER_ALGORITHM_RANGE = 0;

	public int[] keys;

	public double[] values;

	private HyperparameterCombination(int[] keys, double[] values)
	{
		this.keys = keys;
		this.values = values;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < this.keys.length; i++)
		{
			if(this.values[i] != 0)
				sb.append(this.keys[i]).append(":").append(this.values[i]).append(" ");
		}
		return sb.toString();
	}

	public static HyperparameterCombination getInstanceFromInstance(Instance instance)
	{
		int[] keys = instance.getKeys();
		double[] values = instance.getValues();
		int length = 0;
		for(int i = 0; i < keys.length; i++)
		{
			if(keys[i] < HYPERPARAMETER_INDEX_RANGE_MAX)
				length++;
			else
				break;
		}
		return new HyperparameterCombination(Arrays.copyOfRange(keys, 0, length), Arrays.copyOfRange(values, 0, length));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(keys);
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		HyperparameterCombination other = (HyperparameterCombination) obj;
		if(!Arrays.equals(keys, other.keys))
			return false;
		if(!Arrays.equals(values, other.values))
			return false;
		return true;
	}
}
