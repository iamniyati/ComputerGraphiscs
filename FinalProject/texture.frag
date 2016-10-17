#version 150

// Texture mapping fragment shader
//
// Contributor:  Niyati Shah(nxs6032)

// INCOMING DATA
in vec2 TextureCoordinate;
// Add all variables containing data used by the
// fragment shader for lighting and texture mapping

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;

// Texture Variable
uniform sampler2D smile;
//uniform sampler2D frown;


void main()
{
    // Replace with proper texture mapping code
    fragmentColor = texture(smile,TextureCoordinate) ;
    //vec4( 1.0, 1.0, 1.0, 1.0 );   
   // if (!gl_FrontFacing){ // check if it is back face of quad
   // fragmentColor = (texture(frown,TextureCoordinate)); 
   
    
 //  }else{   // check if it is front face of quad
    
 // fragmentColor = (texture(smile,TextureCoordinate));
//}
}
