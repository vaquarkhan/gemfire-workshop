/*
 * Copyright 2011 VMWare.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.vmware.domain;

import com.gemstone.gemfire.cache.EntryOperation;
import com.gemstone.gemfire.cache.PartitionResolver;

import java.io.Serializable;

/**
 * User: Charlie Black
 */
public class LoanKey implements PartitionResolver, Serializable {

    private String key;
    private String routingObject;

    public LoanKey() {
    }

    public LoanKey(String key) {
        this.key = key;
        routingObject = key;
    }

    /**
     * @param opDetails the detail of the entry operation e.g.
     *                  {@link com.gemstone.gemfire.cache.Region#get(Object)}
     * @return object associated with entry operation which allows the Partitioned
     *         Region to store associated data together
     * @throws RuntimeException any exception thrown will terminate the operation and the
     *                          exception will be passed to the calling thread.
     */
    public Serializable getRoutingObject(EntryOperation opDetails) {
        return routingObject;
    }

    /**
     * Returns the name of the PartitionResolver
     *
     * @return String name
     */
    public String getName() {
        return LoanKey.class.getName();
    }

    /**
     * Called when the region containing this callback is closed or destroyed, when
     * the cache is closed, or when a callback is removed from a region
     * using an <code>AttributesMutator</code>.
     * <p/>
     * <p>Implementations should cleanup any external
     * resources such as database connections. Any runtime exceptions this method
     * throws will be logged.
     * <p/>
     * <p>It is possible for this method to be called multiple times on a single
     * callback instance, so implementations must be tolerant of this.
     *
     * @see com.gemstone.gemfire.cache.Cache#close()
     * @see com.gemstone.gemfire.cache.Region#close
     * @see com.gemstone.gemfire.cache.Region#localDestroyRegion()
     * @see com.gemstone.gemfire.cache.Region#destroyRegion()
     * @see com.gemstone.gemfire.cache.AttributesMutator
     */
    public void close() {

    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hashtables such as those provided by
     * <code>java.util.Hashtable</code>.
     * <p/>
     * The general contract of <code>hashCode</code> is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     * an execution of a Java application, the <tt>hashCode</tt> method
     * must consistently return the same integer, provided no information
     * used in <tt>equals</tt> comparisons on the object is modified.
     * This integer need not remain consistent from one execution of an
     * application to another execution of the same application.
     * <li>If two objects are equal according to the <tt>equals(Object)</tt>
     * method, then calling the <code>hashCode</code> method on each of
     * the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     * according to the {@link Object#equals(Object)}
     * method, then calling the <tt>hashCode</tt> method on each of the
     * two objects must produce distinct integer results.  However, the
     * programmer should be aware that producing distinct integer results
     * for unequal objects may improve the performance of hashtables.
     * </ul>
     * <p/>
     * As much as is reasonably practical, the hashCode method defined by
     * class <tt>Object</tt> does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java<font size="-2"><sup>TM</sup></font> programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see java.util.Hashtable
     */
    @Override
    public int hashCode() {
        return key.hashCode();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p/>
     * The <code>equals</code> method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * <code>x</code>, <code>x.equals(x)</code> should return
     * <code>true</code>.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * <code>x</code> and <code>y</code>, <code>x.equals(y)</code>
     * should return <code>true</code> if and only if
     * <code>y.equals(x)</code> returns <code>true</code>.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * <code>x</code>, <code>y</code>, and <code>z</code>, if
     * <code>x.equals(y)</code> returns <code>true</code> and
     * <code>y.equals(z)</code> returns <code>true</code>, then
     * <code>x.equals(z)</code> should return <code>true</code>.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * <code>x</code> and <code>y</code>, multiple invocations of
     * <tt>x.equals(y)</tt> consistently return <code>true</code>
     * or consistently return <code>false</code>, provided no
     * information used in <code>equals</code> comparisons on the
     * objects is modified.
     * <li>For any non-null reference value <code>x</code>,
     * <code>x.equals(null)</code> should return <code>false</code>.
     * </ul>
     * <p/>
     * The <tt>equals</tt> method for class <code>Object</code> implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values <code>x</code> and
     * <code>y</code>, this method returns <code>true</code> if and only
     * if <code>x</code> and <code>y</code> refer to the same object
     * (<code>x == y</code> has the value <code>true</code>).
     * <p/>
     * Note that it is generally necessary to override the <tt>hashCode</tt>
     * method whenever this method is overridden, so as to maintain the
     * general contract for the <tt>hashCode</tt> method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return <code>true</code> if this object is the same as the obj
     *         argument; <code>false</code> otherwise.
     * @see #hashCode()
     * @see java.util.Hashtable
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LoanKey) {
            return key.equals(((LoanKey) obj).key);
        }
        return false;
    }

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
    
    

}
