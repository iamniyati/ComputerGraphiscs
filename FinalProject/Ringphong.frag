#version 150

// Gurard fragment shader
//
// Contributor:  Niyati Shah (nxs6032)

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading for ring

uniform vec4 RingAmbient;
uniform float RingAmbientKa;
uniform vec4 RingAmbientColor;

uniform float RingSpecularKs;
uniform vec4 RingSpecular;
uniform float RingSpecularExpo;

uniform float RingDiffuseKd;
uniform vec4 RingDiffuse;

uniform vec4 RingLightSourcePosition;
uniform vec4 RingLightSourceColor;

// OUTGOING DATA

// final fragment color
out vec4 fragmentColor;


in vec4 lightVec;
in vec4 normalVec;
in vec4 positionVec;
void main()
{
    // Add all necessary code to implement the
    // fragment shader portion of Gurard shading
    
    vec4 N = normalize(normalVec);
    vec4 V = normalize(positionVec);
    vec4 L = normalize(lightVec -positionVec);
    vec4 R = normalize(reflect(L,N));
    
    
    vec4 spec = RingSpecular*RingSpecularKs*pow(max(dot(R,V), 0),RingSpecularExpo);
    vec4 diffusion  = RingDiffuse*RingDiffuseKd*dot(L,N);
    vec4 ambience = RingAmbient*RingAmbientKa*RingAmbientColor;
    
    fragmentColor = ambience+RingLightSourceColor*(diffusion+spec); //vec4( 1.0, 1.0, 1.0, 1.0 );
}
