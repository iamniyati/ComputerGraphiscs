//
// lightingParams.java
//
// Created by Joe Geigel on 1/23/13.
//
// Contributor:  Niyati Shah(nxs6032)
//
// 20155 version
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

public class lightingParams
{
    // Add any data members you need here.

	//Material properties of the teapot:
	//Reflective characteristics of the teapot and the quad:
	public float[] Ambient = {0.5f, 0.1f, 0.9f, 1.0f};
	public float[] Diffuse = {0.89f, 0.0f, 0.0f, 1.0f };
	public float[] Specular = {1.0f, 1.0f, 1.0f, 1.0f };
	
	public float AmbientKa = 0.5f;
	public float DiffuseKd = 0.7f;
	public float SpecularKs = 1.0f;
	
	public float SpecularExpo = 10.0f;
	
	//Properties of the light source:
	public float[] LightSourceColor = {1.0f, 1.0f, 0.0f, 1.0f };
	public float[] LightSourcePosition = {0.0f, 5.0f, 2.0f, 1.0f };
	
	//Ambient light in the scene:
	public float[] AmbientColor = { 0.5f, 0.5f, 0.5f, 1.0f};
    /**
     * constructor
     */
    public lightingParams()
    {
    }

    /**
     * This function sets up the lighting, material, and shading parameters
     * for the Phong shader.
     *
     * You will need to write this function, and maintain all of the values
     * needed to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) shader program to which
     *    parameter values are to be sent
     *
     * @param gl3 - GL3 object on which all OpenGL calls are to be made
     *
     */
    public void setUpPhong (int program, GL3 gl3)
    {
        // Add your code here.
    	
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"Ambient"),1,Ambient,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"Diffuse"),1,Diffuse,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"Specular"),1,Specular,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"LightSourceColor"),1,LightSourceColor,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"LightSourcePosition"),1,LightSourcePosition,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"AmbientColor"),1,AmbientColor,0);
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"AmbientKa"), AmbientKa);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"DiffuseKd"), DiffuseKd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"SpecularKs"), SpecularKs);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"SpecularExpo"), SpecularExpo);
    	
    }
}
