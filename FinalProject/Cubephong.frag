#version 150

// Phong fragment shader
//
// Contributor:  Niyati Shah (nxs6032)

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading
uniform vec4 CubeAmbient;
uniform float CubeAmbientKa;
uniform vec4 CubeAmbientColor;

uniform float CubeSpecularKs;
uniform vec4 CubeSpecular;
uniform float CubeSpecularExpo;

uniform float CubeDiffuseKd;
uniform vec4 CubeDiffuse;

uniform vec4 CubeLightSourcePosition;
uniform vec4 CubeLightSourceColor;

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;
//in vec4 ambience;

in vec4 lightVec;
in vec4 normalVec;
in vec4 positionVec;
void main()
{
    // Add all necessary code to implement the
    // fragment shader portion of Phong shading
    
    vec4 N = normalize(normalVec);
    vec4 V = normalize(positionVec);
    vec4 L = normalize(-1*positionVec);
    vec4 R = normalize(reflect(L,N));
    
    
    vec4 spec = CubeSpecular*CubeSpecularKs*pow(max(dot(R,V), 0),CubeSpecularExpo);
    vec4 diffusion  = CubeDiffuse*CubeDiffuseKd*dot(L,N);
    vec4 ambience = CubeAmbient*CubeAmbientKa*CubeAmbientColor;
    
    fragmentColor = ambience+CubeLightSourceColor*(diffusion+spec); //vec4( 1.0, 1.0, 1.0, 1.0 );
}
