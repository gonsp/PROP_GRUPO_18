package common.domain;

import java.util.ArrayList;
import java.util.Iterator;

// Iterable implementation based on http://stackoverflow.com/a/5849625
public class RelationStructure implements Iterable<Relation>
{
	private ArrayList<Relation> st;
	private int size;

	// Creates an empty RelationStructure
	public RelationStructure()
	{
		st = new ArrayList<Relation>();
		size = 0;
	}

	// Creates a RelationStructure from an existing array of Relations
	public RelationStructure(ArrayList<Relation> r)
	{
		st = new ArrayList<Relation>(r);
		size = r.size();
	}

	// Returns an iterator that iterates over an ArrayList of Relations
	@Override
	public Iterator<Relation> iterator()
	{
		Iterator<Relation> it = new Iterator<Relation>() {
			int pos;

			@Override
			public boolean hasNext()
			{
				return pos < size && st.get(pos) != null;
			}

			@Override
			public Relation next()
			{
				return st.get(++pos);
			}

			@Override
			public void remove()
			{
				st.remove(pos);
			}
		};
		return it;
	}

	// Returns the relation at position i
	public Relation getRelation(int i) throws IndexOutOfBoundsException
	{
		if (i >= st.size())
			throw new IndexOutOfBoundsException("getRelation: index is out " +
					"of range");
		return st.get(i);
	}

	// Removes the relation at position i
	// (ArrayList: every preceding element is shifted to the left)
	public void removeRelation(int i) throws IndexOutOfBoundsException
	{
		if (i >= st.size())
			throw new IndexOutOfBoundsException("removeRelation: index is " +
					"out of range");
		st.remove(i);
		size--;
	}

	// Returns the size of the array
	public int size()
	{
		return size;
	}
}
