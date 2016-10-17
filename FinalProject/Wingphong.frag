#version 150

// Phong fragment shader
//
// Contributor:  Niyati Shah (nxs6032)

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading
uniform vec4 WingAmbient;
uniform float WingAmbientKa;
uniform vec4 WingAmbientColor;

uniform float WingSpecularKs;
uniform vec4 WingSpecular;
uniform float WingSpecularExpo;

uniform float WingDiffuseKd;
uniform vec4 WingDiffuse;

uniform vec4 WingLightSourcePosition;
uniform vec4 WingLightSourceColor;

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
    
    
    vec4 spec = WingSpecular*WingSpecularKs*pow(max(dot(R,V), 0),WingSpecularExpo);
    vec4 diffusion  = WingDiffuse*WingDiffuseKd*dot(L,N);
    vec4 ambience = WingAmbient*WingAmbientKa*WingAmbientColor;
    
    fragmentColor = ambience+WingLightSourceColor*(diffusion+spec); //vec4( 1.0, 1.0, 1.0, 1.0 );
}
