//
//  textureParams.java
//
//  Created by Joe Geigel on 1/23/13.
//
//  Contributor:  Niyati Shah(nxs6032)
//
//  Simple class for setting up the textures for the textures
//  assignment.
//
//  20155 version
//

import java.io.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.texture.*;

public class textureParams
{

    // Add any data members you need here.
	Texture background, planet;
	
	public int[] textureID = {0,0};
    /**
     * constructor
     */
    public textureParams()
    {
    }

    /**
     * This function loads texture data to the GPU.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the various shaders.
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     * @throws FileNotFoundException 
     *
     */
    public void loadTexture( GL3 gl3 )
    {
    	try{
    	// load the background image
    	InputStream smiley =new BufferedInputStream(new FileInputStream("back.jpg")); 
    	background = TextureIO.newTexture(smiley, false,"jpg");
    	
    	
    	}catch(IOException Io){
    		System.out.println("Image not found");
    	}
    	
    }

    /**
     * This function sets up the parameters for texture use.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the various shaders.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which
     *    parameter values are to be sent
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be madete
     *
     */
    public void setUpTexture( int program, GL3 gl3 )
    {
        // Add your code here.
    	
  	
    	// activate and bind background image
    	gl3.glActiveTexture(gl3.GL_TEXTURE0 + 0);
    	background.bind(gl3);
    	gl3.glUniform1i(gl3.glGetUniformLocation(program,"background"), 0);
    	
    	
    	
   	
    }
}
