package plugin.designeditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
	    Shell shell = new Shell(display);
	    shell.setBounds(100, 100, 1000, 600);
	    
	    	    
	    LibgdxDesignEditor obj = new LibgdxDesignEditor(shell);
	    // ------------------------
	    // 
	    
	    obj.create();
	    
	    
	    // ------------------------
//	    shell.pack();
	    shell.open();
	    while( !shell.isDisposed())	
	    {
	      if(!display.readAndDispatch()) 
	      display.sleep();
	    }
	    display.dispose();
	}

}
