package plugin.designeditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class LibgdxDesignEditor {
	public static float PALLETE_PART_WIDTH = 0.3f;
	public static float DRAG_DROP_PART_WIDTH = 0.7f;
	
	public static String PALLETE_NAME = "Pallete";
	public static int DISTANCE_BETWEEN_PALLETE_ITEM = 10; //px;
	public static int PALLETE_EXPAND_BAR_SPACING = 10;
	public static int PALLETE_ITEM_MARGIN_LEFT = 5;
	public static int PALLETE_ITEM_MARGIN_RIGHT = 5;
	public static int PALLETE_ITEM_MARGIN_TOP = 5;
	public static int PALLETE_ITEM_MARGIN_BOTTOM = 5;
	public static int PALLETE_ITEM_CHILD_MARGIN_LEFT = 10;
	public static int PALLETE_ITEM_CHILD_MARGIN_RIGHT =5;
	public static int PALLETE_ITEM_CHILD_MARGIN_TOP = 5;
	public static int PALLETE_ITEM_CHILD_MARGIN_BOTTOM = 5;
	public static int PALLETE_ITEM_CHILD_HEIGHT = 70;
	
	public static float DRAG_DROP_PART_PADDING_LEFT = 100;
	public static float DRAG_DROP_PART_PADDING_RIGHT = 10;
	public static float DRAG_DROP_PART_PADDING_TOP = 10;
	public static float DRAG_DROP_PART_PADDING_BOTTOM = 10;
	
	public static int MOBILE_WIDTH = 480 * 2/3;
	public static int MOBILE_HEIGHT = (int)(800 * 2/3);
	
	public static String[][] palleteItems = {
		{"Groups", "Widget Group", "rootContainer", "Horizontal Group", "Scroll Pane", "Split Pane", "Stack", "Table", "Tree", "Window", "Dialog", "Vertical Group"},
		{"Widgets", "Button", "Image Button", "Image Text Button", "Text Button", "CheckBox", "Image", "Label", "Progress Bar", "Slider", "Select Box", "Text Field", "Text Area"}
	};
	
	private Group palleteGroup;
	private ExpandBar palleteExpandBar;
	
	private Group dragDropGroup; 
	private Group mobileGroup;
	
	private Composite rootContainer;
	
	public LibgdxDesignEditor(Composite parent){
		this.rootContainer = parent;
	}
	
	public void create(){
		createMainLayout();
		createPalletePart();
		createDragdropPart();
	}
	
	/**
	 * Create main layout has type: RowLayout, VERTICAL
	 * Editor main layout have 2 parts: DragDropPart and PalletePart
	 */
	private void createMainLayout(){
		RowLayout mainLayout = new RowLayout(SWT.VERTICAL);
		
		rootContainer.setLayout(mainLayout);
	}
	
	/**
	 * Create PalletePart
	 * PalletePart have the hierarchy like this:
	 * --------------PALLETE---------------- --> Header
	 * - Items 0								!
	 *   + ItemChild 00							!
	 *   + ItemChild 01							!
	 *   + ItemChild 02							!
	 *   ...									!
	 * - Items 1								!	=> Content
	 *   + ItemChild 10							!
	 *   + ItemChild 11							!
	 *   + ItemChild 12							!
	 *   ...									!
	 */
	private void createPalletePart(){
		createMainPalleteLayout();
		createPalleteHeader();
		createPalleteContent();
	}
	
	/**
	 * Create pallete main layout with width = PALLETE_PART_WIDTH * 100 % editorWidth;
	 */
	private void createMainPalleteLayout(){
		palleteGroup = new Group(rootContainer, SWT.SHADOW_ETCHED_OUT);
		palleteGroup.setText(PALLETE_NAME);
		
		RowData data = new RowData((int)(PALLETE_PART_WIDTH * rootContainer.getBounds().width), rootContainer.getBounds().height);
		palleteGroup.setLayoutData(data);
		
		System.out.println("Main Editor: " + rootContainer.getBounds().width + "-" + rootContainer.getBounds().height);
		System.out.println("Main pallete: " + (int)(PALLETE_PART_WIDTH * rootContainer.getBounds().width) + "-"+ rootContainer.getBounds().height);
		
		// Set lay out for palleteGroup 
		palleteGroup.setLayout(new FillLayout(SWT.FULL_SELECTION));
	}
	
	/**
	 * Create pallete Header
	 */
	private void createPalleteHeader(){
	}
	
	/**
	 * Create pallete content have an Expand Bar and add all items to the expand bar
	 */
	private void createPalleteContent(){
		// TODO
		initPalleteExpandBar();
		createPalletExpandBarContent();
	}
	
	/**
	 * Create pallete content with the inited expandbar 
	 */
	private void createPalletExpandBarContent() {
		// TODO Auto-generated method stub
		Composite composite;
		
		for (int i = 0; i < palleteItems.length; i++){
			composite = createPalleteItemComposite(palleteItems[i].length - 1);
			createPalleteItemChilds(composite, palleteItems[i]);
			createPalleteExpandBarItem(composite, palleteItems[i][0]);
		}
	}

	/**
	 * Create a composite to contain all itemchilds of an item
	 * @param length
	 * @return
	 */
	private Composite createPalleteItemComposite(int length) {
		// TODO Auto-generated method stub
		Composite composite = new Composite(palleteExpandBar, SWT.NONE);
		GridLayout layout = new GridLayout();
		
		layout.marginLeft = PALLETE_ITEM_MARGIN_LEFT;
		layout.marginRight = PALLETE_ITEM_MARGIN_RIGHT;
		layout.marginTop = PALLETE_ITEM_MARGIN_TOP;
		layout.marginBottom = PALLETE_ITEM_MARGIN_BOTTOM;
		
		composite.setLayout(layout);
		System.out.println("Create composited!");
		return composite;
	}

	/**
	 * Create all itemchilds of an item
	 * @param composite: parent
	 * @param items: an array contain all name of item childs
	 */
	private void createPalleteItemChilds(Composite composite, String[] items) {
		// TODO Auto-generated method stub
		for (int i = 1; i < items.length; i++){
			createPalleteItemChild(composite, items[i]);
		}
	}
	
	/**
	 * Create item child with name itemName
	 * @param composite: parent
	 * @param itemName: name of item child
	 */
	private void createPalleteItemChild(Composite composite, String itemName) {
		// TODO Auto-generated method stub
		// continue
		Group itemChild = createItemChildGroup(composite);
		Label itemLabel = new Label(itemChild, SWT.NONE);
		
		itemLabel.setText(itemName);
	}
	
	/**
	 * Create group to contain all item childs of an item 
	 * @param composite
	 * @return
	 */
	private Group createItemChildGroup(Composite composite){
		Group group = new Group(composite, SWT.SHADOW_IN);
		
		// set Layout for item child group
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.marginLeft = PALLETE_ITEM_CHILD_MARGIN_LEFT;
		layout.marginRight = PALLETE_ITEM_CHILD_MARGIN_RIGHT;
		layout.marginTop = PALLETE_ITEM_CHILD_MARGIN_TOP;
		layout.marginBottom = PALLETE_ITEM_CHILD_MARGIN_BOTTOM;
		
		group.setLayout(layout);
		
		return group;
	}

	/**
	 * Create an expandbar item with name itemName
	 * @param composite
	 * @param itemName
	 */
	private void createPalleteExpandBarItem(Composite composite, String itemName) {
		// TODO Auto-generated method stub
		ExpandItem item = new ExpandItem(palleteExpandBar, SWT.NONE, 0);
		
		item.setText(itemName);
		item.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item.setControl(composite);
		item.setExpanded(false);
	}

	/**
	 * Init the pallete expandbar
	 */
	private void initPalleteExpandBar() {
		// TODO Auto-generated method stub
		palleteExpandBar = new ExpandBar(palleteGroup, SWT.V_SCROLL);
		palleteExpandBar.setSpacing(PALLETE_EXPAND_BAR_SPACING);
		
		Composite composite = palleteExpandBar.getParent();
		RowData data = new RowData(((RowData)composite.getLayoutData()).width, composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		palleteExpandBar.setLayoutData(data);
		
	}
	
	/**
	 * Create Drag drop part
	 * Drag drop part has the hierarchy like that:
	 * ------------------------------------------------------
	 * !													!
	 * !													!
	 * !													!
	 * !													!
	 * !		  -----------------------------				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !			 DEVICE			  !				!
	 * !		  !		   SIMULATOR		  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  !							  !				!
	 * !		  -----------------------------				!
	 * !													!
	 * ------------------------------------------------------
	 */
	private void createDragdropPart(){
		createDragDropPartMainLayout();
		createDragDropPartContent();
	}

	private void createDragDropPartMainLayout() {
		dragDropGroup = new Group(rootContainer, SWT.V_SCROLL);
		
		RowData data = new RowData((int)(DRAG_DROP_PART_WIDTH * rootContainer.getBounds().width), rootContainer.getBounds().height);
		dragDropGroup.setLayoutData(data);
		
		// set layout for drag drop part
		FormLayout layout = new FormLayout();
		
		layout.marginLeft = (int)DRAG_DROP_PART_PADDING_LEFT;
		layout.marginRight = (int)DRAG_DROP_PART_PADDING_RIGHT;
		layout.marginTop = (int)DRAG_DROP_PART_PADDING_TOP;
		layout.marginBottom = (int)DRAG_DROP_PART_PADDING_BOTTOM;
		
		dragDropGroup.setLayout(layout);
	}
	
	private void createDragDropPartContent(){
		mobileGroup = new Group(dragDropGroup, SWT.NONE);
		
//		FormData data = new FormData((int)(MOBILE_WIDTH * ()dragDropGroup.getLayout().width), MOBILE_HEIGHT);
		FormData data = new FormData(MOBILE_WIDTH, MOBILE_HEIGHT);
		data.top = new FormAttachment(0, (int)DRAG_DROP_PART_PADDING_TOP);
//		data.right = new FormAttachment(0, (int)DRAG_DROP_PART_PADDING_RIGHT);
		data.left = new FormAttachment(0, (int)DRAG_DROP_PART_PADDING_LEFT);
//		data.bottom = new FormAttachment(0, (int)DRAG_DROP_PART_PADDING_BOTTOM);
		
		mobileGroup.setLayoutData(data);
		
		FormLayout layout = new FormLayout();
		
		mobileGroup.setLayout(layout);
		mobileGroup.setBackground(new Color(rootContainer.getDisplay(), 0, 0, 0));
		mobileGroup.setBackgroundImage(new Image(rootContainer.getDisplay(), "img/background.jpg"));
	}
}
