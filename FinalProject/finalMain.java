//
// finalMain.java
//
// Main class for lighting/shading/texturing assignment.
//
//  Contributor: Niyati SHah
//

import java.awt.*;
import java.nio.*;
import java.util.Arrays;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.Animator;

public class finalMain implements GLEventListener, KeyListener
{

    /**
     * We need two vertex buffers and four element buffers:
     * one set for the quad (texture mapped), and one set
     * for the teapot (Phong shaded)
     *
     * Array layout:
     * element 0:  quad
     * element 1:  sphere
     * element 2:  ring
     *  element 3: wing
     *  element 4: tail
     *  element 5: cube
     * 
     */
    private int vbuffer[];
    private int ebuffer[];
    private int numVerts[];

    /**
     * Animation control
     */
    Animator anime;
    boolean animating;

    /**
     * Initial animation rotation angles
     */
    float angles[];
    

    /**
     * Program IDs - current, and all variants
     */
    //public int pshader;
    public int tshader;
    public int planetTexshader;
    public int planetshader;
    public int ringshader;
    public int wingshader;
    public int tailshader;
    public int cubeshader;

    /**
     * Shape info
     */
    shapes myShape;

    /**
     * Lighting information
     */
    lightingParams CubemyPhong;
    lightingParams SpheremyPhong;
    lightingParams RingmyPhong;
    lightingParams TailmyPhong;
    lightingParams WingmyPhong;
    
    int count =0;
    /**
     * Viewing information
     */
    viewParams myView;

    /**
     * Texturing information
     */
    textureParams myTexture;

    /**
     * My canvas
     */
    GLCanvas myCanvas;
    private static Frame frame;

    /**
     * Constructor
     */
    public finalMain( GLCanvas G )
    {
        vbuffer = new int[6];
        ebuffer = new int[6];
        numVerts = new int[6];

        angles = new float[6];
        
        
        animating = false;

        // Initialize array
        angles[0] = 0.0f;
        angles[1] = 0.0f;
        angles[2] = 0.0f;
        angles[3] = 0.0f;
        angles[4] = 0.0f;
   
        myCanvas = G;

        // Initialize lighting for each object
             
         CubemyPhong  = new lightingParams();
         SpheremyPhong = new lightingParams();
         RingmyPhong = new lightingParams();
         TailmyPhong = new lightingParams();
         WingmyPhong = new lightingParams();
         
      // Initialize  view
        myView = new viewParams();
	    myTexture = new textureParams();

        // Set up event listeners
        G.addGLEventListener (this);
        G.addKeyListener (this);
    }
// Error checking
    private void errorCheck (GL3 gl3)
    {
        int code = gl3.glGetError();
        if (code == GL.GL_NO_ERROR)
            System.err.println ("All is well");
        else
            System.err.println ("Problem - error code : " + code);
    }


    /**
     * Simple animate function
     */
    public void animate() {
        angles[shapes.OBJ_RING]-= 0.9;
        angles[shapes.OBJ_SPHERE] += 0.3;
        angles[shapes.OBJ_CUBE] -= 0.3;
        angles[shapes.OBJ_TAIL] +=0.3;
        angles[shapes.OBJ_QUAD] +=0.1;
        
    }

    /**
     * Called by the drawable to initiate OpenGL rendering by the client.
     */
    public void display(GLAutoDrawable drawable)
    {
        // get GL
        GL3 gl3 = (drawable.getGL()).getGL3();

        // clear and draw params..
        gl3.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );

        // first, the quad
        gl3.glUseProgram( tshader );

        // set up viewing and projection parameters
        myView.setUpFrustum( tshader, gl3 );
	
	// set up the texture information
	myTexture.setUpTexture( tshader, gl3 );

        // set up the camera
        myView.setUpCamera( tshader, gl3,
            0.2f, 3.0f, 6.5f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        // set up transformations for the quad
        myView.setUpTransforms( tshader, gl3,
           12f, 12f, 12f,
           0f,0f,angles[shapes.OBJ_QUAD],
            0f,-1f, -10f
        );

        // draw it
        selectBuffers( tshader, shapes.OBJ_QUAD, gl3 );
        gl3.glDrawElements( GL.GL_TRIANGLES, numVerts[shapes.OBJ_QUAD],
            GL.GL_UNSIGNED_SHORT, 0l
        );
  
        
        
///////////////////////SPHERE
	// now, draw the Sphere
        gl3.glUseProgram( planetshader );

        // set up viewing and projection parameters
        myView.setUpFrustum( planetshader, gl3 );

	// set up the Gurard shading information
	SpheremyPhong.setUpPhong( planetshader, gl3 );

        // set up the camera
        myView.setUpCamera( planetshader, gl3,
            0.2f, 3.0f, 6.5f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        myView.setUpTransforms( planetshader, gl3,
            1f,1f,1f,
           0, angles[shapes.OBJ_SPHERE],0,
            1.5f, 0.5f, -1.5f
        );

        // draw it
        selectBuffers( planetshader, shapes.OBJ_SPHERE, gl3 );
        gl3.glDrawElements( GL.GL_TRIANGLES, numVerts[shapes.OBJ_SPHERE],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
//////////////////////////////////////////////////////////
     // now, draw the RING
        gl3.glUseProgram( ringshader );

        // set up viewing and projection parameters
        myView.setUpFrustum( ringshader, gl3 );

	// set up the Gurard shading information
	RingmyPhong.setUpPhong( ringshader, gl3 );

        // set up the camera
        myView.setUpCamera( ringshader, gl3,
            0.2f, 3.0f, 6.5f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        myView.setUpTransforms( ringshader, gl3,
            1f,1f, 1f,
            0, angles[shapes.OBJ_RING],0,
            1.5f, 0.5f, -1.5f
        );

        // draw it
        selectBuffers( ringshader, shapes.OBJ_RING, gl3 );
        gl3.glDrawElements( GL.GL_TRIANGLES, numVerts[shapes.OBJ_RING],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
 ////////////////////////////////////////////////////////////
     // now, draw the WING
        gl3.glUseProgram( wingshader );

        // set up viewing and projection parameters
        myView.setUpFrustum( wingshader, gl3 );

	// set up the Phong shading information
	WingmyPhong.setUpPhong( wingshader, gl3 );

        // set up the camera
        myView.setUpCamera( wingshader, gl3,
            0.2f, 3.0f, 6.5f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        myView.setUpTransforms( wingshader, gl3,
        		0.2f,0.2f, 0.2f,
        		0,0 ,angles[shapes.OBJ_CUBE], // Keep same rotation as cube so they travel together
        		0.0f, 0.0f, 0.0f
        );

        // draw it
        selectBuffers( wingshader, shapes.OBJ_WING, gl3 );
        gl3.glDrawElements( GL.GL_TRIANGLES, numVerts[shapes.OBJ_WING],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        /////////////////////////////////////////////////////////////
     // now, draw the TAIL
        gl3.glUseProgram( tailshader );

        // set up viewing and projection parameters
        myView.setUpFrustum( tailshader, gl3 );

	// set up the Phong shading information
	TailmyPhong.setUpPhong( tailshader, gl3 );

        // set up the camera
        myView.setUpCamera( tailshader, gl3,
            0.2f, 3.0f, 6.5f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        myView.setUpTransforms( tailshader, gl3,
        		0.2f,0.2f, 0.2f,
        		0,0 ,angles[shapes.OBJ_CUBE],// Keep same rotation as cube so they travel together
        		0.0f, 0.0f, 0.0f
        );

        // draw it
        selectBuffers( tailshader, shapes.OBJ_TAIL, gl3 );
        gl3.glDrawElements( GL.GL_TRIANGLES, numVerts[shapes.OBJ_TAIL],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        
//////////////////////////////
     // now, draw the CUBE
        gl3.glUseProgram( cubeshader );

        // set up viewing and projection parameters
        myView.setUpFrustum( cubeshader, gl3 );

	// set up the Phong shading information
	CubemyPhong.setUpPhong( cubeshader, gl3 );

        // set up the camera
        myView.setUpCamera( cubeshader, gl3,
            0.2f, 3.0f, 6.5f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f
        );

        myView.setUpTransforms( cubeshader, gl3,
        		0.2f,0.2f, 0.2f,
        		0,0 ,angles[shapes.OBJ_CUBE], // Keep same rotation as cube so they travel together
            0f, 0f, 0f
        );

        // draw it
        selectBuffers( cubeshader, shapes.OBJ_CUBE, gl3 );
        gl3.glDrawElements( GL.GL_TRIANGLES, numVerts[shapes.OBJ_CUBE],
            GL.GL_UNSIGNED_SHORT, 0l
        );
        // perform any required animation for the next time
        if( animating ) {
            animate();
        }
    }

    /**
     * Notifies the listener to perform the release of all OpenGL
     * resources per GLContext, such as memory buffers and GLSL
     * programs.
     */
    public void dispose( GLAutoDrawable drawable )
    {
    }

    /**
     * Verify shader creation
     */
    private void checkShaderError( shaderSetup myShaders, int program,
        String which )
    {
        if( program == 0 ) {
            System.err.println( "Error setting " + which +
                " shader - " +
                myShaders.errorString(myShaders.shaderErrorCode)
            );
            System.exit( 1 );
        }
    }

    /**
     * Called by the drawable immediately after the OpenGL context is
     * initialized.
     */
    public void init( GLAutoDrawable drawable )
    {
        // get the gl object
        GL3 gl3 = drawable.getGL().getGL3();

        // create the Animator now that we have the drawable
        anime = new Animator( drawable );

	// Load texture image(s)
	myTexture.loadTexture( gl3 );

        // Load shaders, verifying each
        shaderSetup myShaders = new shaderSetup();

        tshader = myShaders.readAndCompile(gl3,"texture.vert","texture.frag");
        checkShaderError( myShaders, tshader, "texture" );
        
   /////// ////////    
        cubeshader = myShaders.readAndCompile( gl3, "Cubephong.vert", "Cubephong.frag" ); // for cube
        checkShaderError( myShaders, cubeshader, "Cubephong" );
        
        tailshader = myShaders.readAndCompile( gl3, "Tailphong.vert", "Tailphong.frag" );// for tail
        checkShaderError( myShaders, tailshader, "Tailphong" );
        
        wingshader = myShaders.readAndCompile( gl3, "Wingphong.vert", "Wingphong.frag" );// for wing
        checkShaderError( myShaders, wingshader, "Wingphong" );
        
        ringshader = myShaders.readAndCompile( gl3, "Ringphong.vert", "Ringphong.frag" ); // for ring
        checkShaderError( myShaders, ringshader, "Ringphong" );
        
        planetshader = myShaders.readAndCompile( gl3, "Spherephong.vert", "Spherephong.frag" ); // for planet
        checkShaderError( myShaders, planetshader, "Spherephong" );

        // Create all our objects
        createShape( gl3, shapes.OBJ_QUAD );
        createShape( gl3, shapes.OBJ_SPHERE );
        createShape( gl3, shapes.OBJ_RING );
        createShape( gl3, shapes.OBJ_CUBE );
        createShape( gl3, shapes.OBJ_WING );
        createShape( gl3, shapes.OBJ_TAIL );

        // Other GL initialization
        gl3.glEnable( GL.GL_DEPTH_TEST );
        gl3.glFrontFace( GL.GL_CCW );
	gl3.glPolygonMode( GL.GL_FRONT_AND_BACK, GL2GL3.GL_FILL );
        gl3.glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );
        gl3.glDepthFunc( GL.GL_LEQUAL );
        gl3.glClearDepth( 1.0f );
    }

    /**
     * Called by the drawable during the first repaint after the component
     * has been resized.
     */
    public void reshape( GLAutoDrawable drawable, int x, int y,
        int width, int height )
    {
    }

    /**
     * Create a vertex or element array buffer
     *
     * @param target - which type of buffer to create
     * @param data   - source of data for buffer (or null)
     * @param size   - desired length of buffer
     */
    int makeBuffer( int target, Buffer data, long size, GL3 gl3 )
    {
        int buffer[] = new int[1];

        gl3.glGenBuffers( 1, buffer, 0 );
        gl3.glBindBuffer( target, buffer[0] );
        gl3.glBufferData( target, size, data, GL.GL_STATIC_DRAW );

	return( buffer[0] );
    }

    /**
     * Create vertex and element buffers for a shape
     */
    public void createShape( GL3 gl3, int obj )
    {
        // clear the old shape
        myShape = new shapes();

        // make the shape
        myShape.makeShape( obj );

        // save the vertex count
        numVerts[obj] = myShape.nVertices();

        // get the vertices for the shape
        Buffer points = myShape.getVertices();
        long dataSize = numVerts[obj] * 4l * 4l;

        // get the normals for the shape
        Buffer normals = myShape.getNormals();
        long ndataSize = numVerts[obj] * 3l * 4l;

        // there may or may not be (u,v) information
        Buffer uv = myShape.getUV();
        long uvdataSize = 0;
	if( obj == shapes.OBJ_QUAD  ) {
	    uvdataSize = numVerts[obj] * 2l * 4l;
	}

        // get the element data
        Buffer elements = myShape.getElements();
        long edataSize = numVerts[obj] * 2l;

        long totalSize = dataSize + ndataSize + uvdataSize;

        vbuffer[obj] = makeBuffer( GL.GL_ARRAY_BUFFER, null, totalSize, gl3 );
        gl3.glBufferSubData( GL.GL_ARRAY_BUFFER, 0, dataSize, points );
        gl3.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize, ndataSize, normals );
	if( obj == shapes.OBJ_QUAD  ) {
            gl3.glBufferSubData( GL.GL_ARRAY_BUFFER, dataSize+ndataSize,
	        uvdataSize, uv );
	}

        // generate the element buffer
        ebuffer[obj] = makeBuffer( GL.GL_ELEMENT_ARRAY_BUFFER,
	    elements, edataSize, gl3 );

    }

    /**
     * Bind the correct vertex and element buffers
     *
     * Assumes the correct shader program has already been enabled
     */
    private void selectBuffers( int program, int obj, GL3 gl3 )
    {
        // bind the buffers
        gl3.glBindBuffer( GL.GL_ARRAY_BUFFER, vbuffer[obj] );
        gl3.glBindBuffer( GL.GL_ELEMENT_ARRAY_BUFFER, ebuffer[obj] );

        // calculate the number of bytes of vertex and normal data
        long dataSize = numVerts[obj] * 4l * 4l;
	long ndataSize = numVerts[obj] * 3l * 4l;

        // set up the vertex attribute variables
        int vPosition = gl3.glGetAttribLocation( program, "vPosition" );
        gl3.glEnableVertexAttribArray( vPosition );
        gl3.glVertexAttribPointer( vPosition, 4, GL.GL_FLOAT, false,
                                       0, 0l );

        int vNormal = gl3.glGetAttribLocation( program, "vNormal" );
        gl3.glEnableVertexAttribArray( vNormal );
        gl3.glVertexAttribPointer( vNormal, 3, GL.GL_FLOAT, false,
                                   0, dataSize );

	if( obj == shapes.OBJ_QUAD ) {
            int vTexCoord = gl3.glGetAttribLocation( program, "vTexCoord" );
            gl3.glEnableVertexAttribArray( vTexCoord );
            gl3.glVertexAttribPointer( vTexCoord, 2, GL.GL_FLOAT, false,
                                       0, dataSize+ndataSize );
	}

    }

    /**
     * Because I am a Key Listener...we'll only respond to key presses
     */
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){}

    /**
     * Invoked when a key has been pressed.
     */
    public void keyPressed(KeyEvent e)
    {
        // Get the key that was pressed
        char key = e.getKeyChar();

        // Respond appropriately
        switch( key ) {

            case 'a':    // animate
                animating = true;
                anime.start();
                break;

            case 'r':    // reset rotations
                angles[0] = 0.0f;
                angles[1] = 0.0f;
                break;

            case 's':    // stop animating
                animating = false;
                anime.stop();
                break;

            case 'q': case 'Q':
		frame.dispose();
                System.exit( 0 );
                break;
        }

        // do a redraw
        myCanvas.display();
    }

    /**
     * main program
     */
    public static void main(String [] args)
    {
        // GL setup
        GLProfile glp = GLProfile.get( GLProfile.GL3 );
        GLCapabilities caps = new GLCapabilities( glp );
        GLCanvas canvas = new GLCanvas( caps );

        // create your tessMain
        finalMain myMain = new finalMain(canvas);

        frame = new Frame( "Final Project" );
        frame.setSize( 600, 600 );
        frame.add( canvas );
        frame.setVisible( true );

        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
		frame.dispose();
                System.exit(0);
            }
        } );
    }
}
