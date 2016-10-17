#version 150

// Gurard fragment shader
//
// Contributor:  Niyati Shah (nxs6032)

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading
uniform vec4 SphereAmbient;
uniform float SphereAmbientKa;
uniform vec4 SphereAmbientColor;

uniform float SphereSpecularKs;
uniform vec4 SphereSpecular;
uniform float SphereSpecularExpo;

uniform float SphereDiffuseKd;
uniform vec4 SphereDiffuse;

uniform vec4 SphereLightSourcePosition;
uniform vec4 SphereLightSourceColor;

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
    
    
    vec4 spec = SphereSpecular*SphereSpecularKs*pow(max(dot(R,V), 0),SphereSpecularExpo);
    vec4 diffusion  = SphereDiffuse*SphereDiffuseKd*dot(L,N);
    vec4 ambience = SphereAmbient*SphereAmbientKa*SphereAmbientColor;
    
    fragmentColor = ambience+SphereLightSourceColor*(diffusion+spec); //vec4( 1.0, 1.0, 1.0, 1.0 );
}
