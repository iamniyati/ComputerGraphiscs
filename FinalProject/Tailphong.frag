#version 150

// Phong fragment shader
//
// Contributor:  Niyati Shah (nxs6032)

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading
uniform vec4 TailAmbient;
uniform float TailAmbientKa;
uniform vec4 TailAmbientColor;

uniform float TailSpecularKs;
uniform vec4 TailSpecular;
uniform float TailSpecularExpo;

uniform float TailDiffuseKd;
uniform vec4 TailDiffuse;

uniform vec4 TailLightSourcePosition;
uniform vec4 TailLightSourceColor;

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
    
    
    vec4 spec = TailSpecular*TailSpecularKs*pow(max(dot(R,V), 0),TailSpecularExpo);
    vec4 diffusion  = TailDiffuse*TailDiffuseKd*dot(L,N);
    vec4 ambience = TailAmbient*TailAmbientKa*TailAmbientColor;
    
    fragmentColor = ambience+TailLightSourceColor*(diffusion+spec); //vec4( 1.0, 1.0, 1.0, 1.0 );
}
