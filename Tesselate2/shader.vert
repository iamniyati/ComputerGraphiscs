
//
// Vertex shader for the transformation assignment
//
// Author:  W. R. Carithers
//
// Contributor:  Niyati Shah

#version 150

// attribute:  vertex position
in vec4 vPosition;

//Rotation angle
uniform vec3 theta;

//get the projection parameter
  uniform float projection;


  
float Left = -1.0;
float Right = 1.0;
float Top = 1.0;
float Bottom = -1.0;
float Near = 0.9;
float Far = 4.5;
 
uniform vec3  eyepoint;
uniform vec3  lookout;
uniform vec3  up;
uniform vec3  scVec;
uniform vec3  rVec;
uniform vec3  tVec;

// add other global shader variables to hold the
// parameters your application code is providing



	
//Set up the frustum projection	

 void setupFrustum(out mat4 perspective){
 
    perspective = mat4( (2*Near/(Right-Left))     , 0.0 				       ,  0.0 		  ,   0.0,
					0.0					     , (2*Near/(Top-Bottom))   ,  0.0 				     ,   0.0,
					(Right+Left)/(Right-Left), (Top+Bottom)/(Top-Bottom), -(Far+Near)/(Far-Near)  , -1.0,
					0.0						 , 0.0					   , -(2*Far*Near)/(Far-Near),   0.0  );
					
	
	}
 //Set up the orthographic projection
 
 void setupOrtho(out mat4 ortho){
 
 	ortho = mat4(   2/(Right-Left)		  ,   0.0						 ,   0.0					 , 0.0,
					  0.0                       ,   2/(Top-Bottom)		 ,   0.0					 , 0.0,
					  0.0					 	  ,   0.0						 , -2/(Far-Near)         , 0.0,
					-(Right+Left)/(Right-Left), -(Top+Bottom)/(Top-Bottom), -(Far+Near)/(Far-Near), 1.0  );					
	}
	
	//create Rotate transform matrix
	void rotate(out mat4 rMat){
	
	    vec3 angles = radians( theta );
    	vec3 c = cos( angles );
    	vec3 s = sin( angles );
 
 
	mat4 rx = mat4 ( 1.0,  0.0,  0.0,  0.0,
                     0.0,  c.x,  s.x,  0.0,
                     0.0, -s.x,  c.x,  0.0,
                     0.0,  0.0,  0.0,  1.0 );

    mat4 ry = mat4 ( c.y,  0.0, -s.y,  0.0,
                     0.0,  1.0,  0.0,  0.0,
                     s.y,  0.0,  c.y,  0.0,
                     0.0,  0.0,  0.0,  1.0 );

    mat4 rz = mat4 ( c.z,  s.z,  0.0,  0.0,
                    -s.z,  c.z,  0.0,  0.0,
                     0.0,  0.0,  1.0,  0.0,
                     0.0,  0.0,  0.0,  1.0 );
                     
           rMat = rz*ry*rx;
	}
				
   //create Translation transform matrix
 void translate(out mat4 tMat){
 		tMat = mat4(  1.0, 0.0, 0.0, 0.0,
				 0.0, 1.0, 0.0, 0.0,
	 			 0.0, 0.0, 1.0, 0.0,
				 tVec.x, tVec.y, tVec.z, 1.0);
 }
 
 //create Scale transform matrix
 void scale(out mat4 scMat){
 	scMat = mat4(       scVec.x,0.0,  0.0, 0.0,
					 	0.0, scVec.y, 0.0, 0.0,
						0.0, 0.0, scVec.z, 0.0,
						0.0, 0.0, 0.0,     1.0);
						}

void main()
{
	
	 mat4 view, perspective,ortho,scMat,rMat,tMat;
     	
    // set viewing	   	
   
    vec3 n = normalize(eyepoint-lookout);
	vec3 u = normalize(cross(up,n));
	vec3 v = cross(n,u);
						    
     view = mat4(   u.x			       , v.x			   ,  n.x			   , 0,
				    u.y				   , v.y			   ,  n.y			   , 0,
				    u.z				   , v.z			   ,  n.z			   , 0,
				    -dot(u, eyepoint), -dot(v,eyepoint),  -dot(n,eyepoint)     , 1 );
		
    
    // set transformation
    
    //Scaling transformation	
		scale(scMat);	
 	//Rotation transformation	
  		rotate(rMat);
  	//Translate transformation		
  		translate(tMat);        

  	mat4 transform = (tMat*rMat*scMat);
					
        
       //choose projection
     if(projection ==0.0f ){
    		setupFrustum(perspective);
    		gl_Position = perspective*view*transform*vPosition;
    }else {
    			setupOrtho(ortho);
    			gl_Position = ortho*view*transform*vPosition;
   	}
							
	
							
    
}
