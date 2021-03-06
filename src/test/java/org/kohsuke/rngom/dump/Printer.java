package org.kohsuke.rngom.dump;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import org.kohsuke.rngom.ast.builder.Annotations;
import org.kohsuke.rngom.ast.builder.CommentList;
import org.kohsuke.rngom.ast.builder.DataPatternBuilder;
import org.kohsuke.rngom.ast.builder.Div;
import org.kohsuke.rngom.ast.builder.ElementAnnotationBuilder;
import org.kohsuke.rngom.ast.builder.Grammar;
import org.kohsuke.rngom.ast.builder.Include;
import org.kohsuke.rngom.ast.om.Location;
import org.kohsuke.rngom.ast.om.ParsedElementAnnotation;
import org.kohsuke.rngom.ast.om.ParsedNameClass;
import org.kohsuke.rngom.ast.om.ParsedPattern;

/**
 * 
 * @author
 *      Kohsuke Kawaguchi (kk@kohsuke.org)
 */
public class Printer {
    private final PrintWriter out;
    private boolean hasParams = false;
    
    public Printer( PrintWriter out ) {
        this.out = out;
    }
    
    public Printer( Writer out ) {
        this.out = new PrintWriter(out);
    }
    
    public Printer( OutputStream out ) {
        this(new OutputStreamWriter(out));
    }
    
    public Printer object(String name) {
        out.print(name);
        out.print('.');
        return this;
    }
    
    public Printer name( String name ) {
        out.print(name+'(');
        return this;
    }
    
    public Printer param( Object o ) {
        if(hasParams)   out.print(',');
        if(o instanceof String) {
            out.print('"');
            out.print(o);
            out.print('"');
        } else {
            out.print(o);
        }
        hasParams = true;
        return this;
    }

    public Printer param(int i) {
        return param(new Integer(i));
    }
    
    private Object r( Object r ) {
        out.print(") -> ");
        out.print(r);
        out.println();
        out.flush();
        hasParams = false;
        return r;
    }
    
    public void result() {
        out.println(")");
        out.flush();
        hasParams = false;
    }
    
    public ParsedPattern result( ParsedPattern p ) {
        r(p);
        return p;
    }
    
    public ParsedNameClass result( ParsedNameClass nc ) {
        r(nc);
        return nc;
    }
    
    public Location result( Location l ) {
        r(l);
        return l;
    }
    
    public Annotations result( Annotations a ) {
        r(a);
        return a;
    }
    
    public CommentList result( CommentList c ) {
        r(c);
        return c;
    }
    
    public Grammar result( Grammar g ) {
        r(g);
        return g;
    }
    
    public Div result( Div d ) {
        r(d);
        return d;
    }
    
    public Include result( Include i ) {
        r(i);
        return i;
    }
    
    public ElementAnnotationBuilder result( ElementAnnotationBuilder eab ) {
        r(eab);
        return eab;
    }
    
    public ParsedElementAnnotation result( ParsedElementAnnotation a ) {
        r(a);
        return a;
    }

    public DataPatternBuilder result(DataPatternBuilder builder) {
        r(builder);
        return builder;
    }
}
