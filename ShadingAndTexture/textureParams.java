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
	Texture smile, frown;
	
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
    	// load the smiley image
    	InputStream smiley = new BufferedInputStream(new FileInputStream("smiley2.png")); 
    	 smile = TextureIO.newTexture(smiley, false,"png");
    	// load the frowny image
    	 InputStream frowny = new BufferedInputStream(new FileInputStream("frowny2.png")); 
    	 frown = TextureIO.newTexture(frowny, false,"png");
    	
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
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void setUpTexture( int program, GL3 gl3 )
    {
        // Add your code here.
    	
    	    	
       	/*int smileImage = gl3.glGetUniformLocation(program,"smileImage");
    	gl3.glUniform1i(smileImage, 0);
    	gl3.glActiveTexture(gl3.GL_TEXTURE0 + 0);
    	gl3.glBindTexture(gl3.GL_TEXTURE_2D, program);
    	smile.enable(gl3);
    	smile.bind(gl3);
    	
    	 int frownImage = gl3.glGetUniformLocation(program,"frownImage");
    	gl3.glUniform1i(frownImage, 2);
    	gl3.glActiveTexture(gl3.GL_TEXTURE2 + 2);
    	gl3.glBindTexture(gl3.GL_TEXTURE_2D, program);
    	
    	frown.enable(gl3);
    	frown.bind(gl3);*/
    	
    	// activate and bind smiley image
    	gl3.glActiveTexture(gl3.GL_TEXTURE2 + 0);
    	smile.bind(gl3);
    	gl3.glUniform1i(gl3.glGetUniformLocation(program,"smile"), 0);
    	
    	// activate and bind frowny image
    	gl3.glActiveTexture(gl3.GL_TEXTURE2 + 2);
    	frown.bind(gl3);
    	gl3.glUniform1i( gl3.glGetUniformLocation(program,"frown"), 2);
    	
   	
    }
}
