
//
//viewParams.java
//
//Created by Joe Geigel on 1/23/13.
//
//Contributor:  Niyati Shah
//20155
//


import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class viewParams
{

    ///
    // constructor
    ///
    public viewParams()
    {
    	
    	
    }


    ///
    // This function sets up the view and projection parameter for a frustum
    // projection of the scene. See the assignment description for the values
    // for the projection parameters.
    //
    // You will need to write this function, and maintain all of the values
    // needed to be sent to the vertex shader.
    //
    // @param program - The ID of an OpenGL (GLSL) shader program to which
    //    parameter values are to be sent
    //
    // @param gl3 - GL3 object on which all OpenGL calls are to be made
    //
    ///
    public void setUpFrustum (int program, GL3 gl3)
    {
        // Add your code here.
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program, "projection"),0);
    	
    	
    }

    ///
    // This function sets up the view and projection parameter for an
    // orthographic projection of the scene. See the assignment description
    // for the values for the projection parameters.
    //
    // You will need to write this function, and maintain all of the values
    // needed to be sent to the vertex shader.
    //
    // @param program - The ID of an OpenGL (GLSL) shader program to which
    //    parameter values are to be sent
    //
    // @param gl3 - GL3 object on which all OpenGL calls are to be made
    //
    ///
    public void setUpOrtho (int program, GL3 gl3)
    {
        // Add your code here.
    
    	gl3.glUniform1f(gl3.glGetUniformLocation(program, "projection"),1);
     }


    ///
    // This function clears any transformations, setting the values to the
    // defaults: no scaling (scale factor of 1), no rotation (degree of
    // rotation = 0), and no translation (0 translation in each direction).
    //
    // You will need to write this function, and maintain all of the values
    // which must be sent to the vertex shader.
    //
    // @param program - The ID of an OpenGL (GLSL) shader program to which
    //    parameter values are to be sent
    // @param gl3 - GL3 object on which all OpenGL calls are to be made
    ///
    public void clearTransforms( int program, GL3 gl3 )
    {
        // Add your code here.
    	float[] scale = {1f,1f,1f};
    	float[] rotate = {0f,0f,0f};
    	float[] translate=  {0f,0f,0f}; 
    	
    
    	
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "scVec"), 1, scale,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "rVec"), 1, rotate,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "tVec"), 1, translate,0);
    
    }


    ///
    // This function sets up the transformation parameters for the vertices
    // of the teapot.  The order of application is specified in the driver
    // program.
    //
    // You will need to write this function, and maintain all of the values
    // which must be sent to the vertex shader.
    //
    // @param program - The ID of an OpenGL (GLSL) shader program to which
    //    parameter values are to be sent
    // @param gl3 - GL3 object on which all OpenGL calls are to be made
    // @param scaleX - amount of scaling along the x-axis
    // @param scaleY - amount of scaling along the y-axis
    // @param scaleZ - amount of scaling along the z-axis
    // @param rotateX - angle of rotation around the x-axis, in degrees
    // @param rotateY - angle of rotation around the y-axis, in degrees
    // @param rotateZ - angle of rotation around the z-axis, in degrees
    // @param translateX - amount of translation along the x axis
    // @param translateY - amount of translation along the y axis
    // @param translateZ - amount of translation along the z axis
    ///
    public void setUpTransforms( int program, GL3 gl3,
        float scaleX, float scaleY, float scaleZ,
        float rotateX, float rotateY, float rotateZ,
        float translateX, float translateY, float translateZ )
    {
        // Add your code here.
    	float[] scale = {scaleX, scaleY, scaleZ};
    	float[] rotate = { rotateX,rotateY,rotateZ};
    	float[] translate = { translateX,translateY,translateZ};
    	
    	
    	
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "scVec"),1,  scale,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "rVec"), 1,  rotate,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "tVec"), 1,  translate,0);
    	
    }


    ///
    // This function clears any changes made to camera parameters, setting the
    // values to the defaults: eye (0.0,0.0,0.0), lookat (0,0,0.0,-1.0),
    // and up vector (0.0,1.0,0.0).
    //
    // You will need to write this function, and maintain all of the values
    // which must be sent to the vertex shader.
    //
    // @param program - The ID of an OpenGL (GLSL) shader program to which
    //    parameter values are to be sent
    // @param gl3 - GL3 object on which all OpenGL calls are to be made
    ///
    void clearCamera( int program, GL3 gl3 )
    {
        // Add your code here.
    	float[] eye = {0f,0f,0f};
    	float[] lookat= {0f,0f,-1f};
    	float[] upVector = {0f,1f,0f};
    
    	
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "eyepoint"), 1, eye,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "lookout"), 1, lookat,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "up"), 1, upVector,0);
    	
    }


    ///
    // This function sets up the camera parameters controlling the viewing
    // transformation.
    //
    // You will need to write this function, and maintain all of the values
    // which must be sent to the vertex shader.
    //
    // @param program - The ID of an OpenGL (GLSL) shader program to which
    //    parameter values are to be sent
    // @param gl3 - GL3 object on which all OpenGL calls are to be made
    // @param eyeX - x coordinate of the camera location
    // @param eyeY - y coordinate of the camera location
    // @param eyeZ - z coordinate of the camera location
    // @param lookatX - x coordinate of the lookat point
    // @param lookatY - y coordinate of the lookat point
    // @param lookatZ - z coordinate of the lookat point
    // @param upX - x coordinate of the up vector
    // @param upY - y coordinate of the up vector
    // @param upZ - z coordinate of the up vector
    ///
    void setUpCamera( int program, GL3 gl3,
        float eyeX, float eyeY, float eyeZ,
        float lookatX, float lookatY, float lookatZ,
        float upX, float upY, float upZ )
    {
        // Add your code here.
    	float[] eye = {eyeX,eyeY,eyeZ};
    	float[] lookat= {lookatX,lookatY,lookatZ};
    	float[] upVector = {upX,upY,upZ};
    	
    	
    	
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "eyepoint"), 1, eye,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "lookout"), 1, lookat,0);
    	gl3.glUniform3fv(gl3.glGetUniformLocation(program, "up"), 1, upVector,0);
    }

}
					  				   
