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

	//Material properties of the cube:
	//Reflective characteristics of the cube
	
	public float[] CubeAmbient = {1f, 1f, 1f, 1.0f};
	public float[] CubeDiffuse = {1f,1f, 1f, 1.0f};
	public float[] CubeSpecular = {1f, 1f, 1f, 1.0f };
	public float CubeAmbientKa = 0.5f;
	public float CubeDiffuseKd = 0.7f;
	public float CubeSpecularKs = 1f;
	public float CubeSpecularExpo = 10.0f;
	
	//Material properties of the cube:
		//Reflective characteristics of the cube
	public float[] RingAmbient = {0.5f, 0.1f, 0.9f, 1.0f};
	public float[] RingDiffuse = {0.89f, 0.0f, 0.0f, 1.0f };
	public float[] RingSpecular = {1.0f, 1.0f, 1.0f, 1.0f };
	public float RingAmbientKa = 0.5f;
	public float RingDiffuseKd = 0.7f;
	public float RingSpecularKs = 1.0f;
	public float RingSpecularExpo = 10.0f;
	
	//Material properties of the cube:
		//Reflective characteristics of the cube
	public float[] SphereAmbient = {1f, 1f, 1f, 1.0f};
	public float[] SphereDiffuse = {1f,1f, 1f, 1f };
	public float[] SphereSpecular = {1f,1f, 1f, 1f };
	public float SphereAmbientKa = 0.5f;
	public float SphereDiffuseKd = 0.7f;
	public float SphereSpecularKs = 0.1f;
	public float SphereSpecularExpo = 10.0f;
	
	//Material properties of the cube:
		//Reflective characteristics of the cube
	public float[] WingAmbient = {0.5f, 0.1f, 0.9f, 1.0f};
	public float[] WingDiffuse = {0.89f, 0.0f, 0.0f, 1.0f };
	public float[] WingSpecular = {1.0f, 1.0f, 1.0f, 1.0f };
	public float WingAmbientKa = 0.5f;
	public float WingDiffuseKd = 0.7f;
	public float WingSpecularKs = 0.1f;
	public float WingSpecularExpo = 10.0f;
	
	//Material properties of the cube:
		//Reflective characteristics of the cube
	public float[] TailAmbient = {0.5f, 0.1f, 0.9f, 1.0f};
	public float[] TailDiffuse = {0.89f, 0.0f, 0.0f, 1.0f };
	public float[] TailSpecular = {1.0f, 1.0f, 1.0f, 1.0f };
	public float TailAmbientKa = 0.5f;
	public float TailDiffuseKd = 0.7f;
	public float TailSpecularKs = 0.1f;
	public float TailSpecularExpo = 10.0f;
	
	
	//Properties of the light source:
	
	public float[] CubeLightSourceColor = {1.0f, 1.0f, 1.0f, 1.0f }; // cube
	
	public float[] RingLightSourceColor = {1.0f, 1.0f, 1.0f, 1.0f  }; //ring
	
	public float[] SphereLightSourceColor = {1f, 1.0f, 0f, 1f }; // sphere
	
	public float[] WingLightSourceColor = {1.0f, 1.0f, 1f, 1.0f }; // wing
	
	public float[] TailLightSourceColor = {1.0f, 1.0f, 1.0f, 1.0f }; // tail
	
	//Ambient light in the scene:
	public float[] CubeAmbientColor = { 0.5f, 0.5f, 0.5f, 1.0f }; // cube
	public float[] RingAmbientColor = {  0.5f, 0.5f, 0.5f, 1.0f };	// ring
	public float[] SphereAmbientColor = {  0.5f, 0.5f, 0.5f, 1.0f }; // sphere
	public float[] WingAmbientColor = {  0.5f, 0.5f, 0.5f, 1.0f };// wing
	public float[] TailAmbientColor = {  0.5f, 0.5f, 0.5f, 1.0f }; // tail
	
	
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
/////////// Cube Object    	
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"CubeAmbient"),1,CubeAmbient,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"CubeDiffuse"),1,CubeDiffuse,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"CubeSpecular"),1,CubeSpecular,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"CubeLightSourceColor"),1,CubeLightSourceColor,0);    	
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"CubeAmbientColor"),1,CubeAmbientColor,0);
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"CubeAmbientKa"), CubeAmbientKa);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"CubeDiffuseKd"), CubeDiffuseKd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"CubeSpecularKs"), CubeSpecularKs);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"CubeSpecularExpo"), CubeSpecularExpo);
    	
///////////// Ring object
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"RingAmbient"),1,RingAmbient,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"RingDiffuse"),1,RingDiffuse,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"RingSpecular"),1,RingSpecular,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"RingLightSourceColor"),1,RingLightSourceColor,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"RingAmbientColor"),1,RingAmbientColor,0);
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"RingAmbientKa"), RingAmbientKa);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"RingDiffuseKd"), RingDiffuseKd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"RingSpecularKs"), RingSpecularKs);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"RingSpecularExpo"), RingSpecularExpo);
    	
///////////// Wing Object
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"WingAmbient"),1,WingAmbient,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"WingDiffuse"),1,WingDiffuse,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"WingSpecular"),1,WingSpecular,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"WingLightSourceColor"),1,WingLightSourceColor,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"WingAmbientColor"),1,WingAmbientColor,0);
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"WingAmbientKa"), WingAmbientKa);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"WingDiffuseKd"), WingDiffuseKd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"WingSpecularKs"), WingSpecularKs);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"WingSpecularExpo"), WingSpecularExpo);
    	
 //////////// Tail Object
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"TailTailAmbient"),1,TailAmbient,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"TailDiffuse"),1,TailDiffuse,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"TailSpecular"),1,TailSpecular,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"TailLightSourceColor"),1,TailLightSourceColor,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"TailAmbientColor"),1,TailAmbientColor,0);
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"TailAmbientKa"), TailAmbientKa);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"TailDiffuseKd"), TailDiffuseKd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"TailSpecularKs"), TailSpecularKs);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"TailSpecularExpo"), TailSpecularExpo);
    	
 ////////////// Sphere Object
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"SphereAmbient"),1,SphereAmbient,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"SphereDiffuse"),1,SphereDiffuse,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"SphereSpecular"),1,SphereSpecular,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"SphereLightSourceColor"),1,SphereLightSourceColor,0);
    	gl3.glUniform4fv(gl3.glGetUniformLocation(program,"SphereAmbientColor"),1,SphereAmbientColor,0);
    	
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"SphereAmbientKa"), SphereAmbientKa);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"SphereDiffuseKd"), SphereDiffuseKd);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"SphereSpecularKs"), SphereSpecularKs);
    	gl3.glUniform1f(gl3.glGetUniformLocation(program,"SphereSpecularExpo"),SphereSpecularExpo);
    	
    }
}
