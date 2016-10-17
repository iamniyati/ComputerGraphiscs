#version 150

// Phong fragment shader
//
// Contributor:  Niyati Shah (nxs6032)

// INCOMING DATA

// Add all variables containing data used by the
// fragment shader for lighting and shading
uniform vec4 Ambient;
uniform float AmbientKa;
uniform vec4 AmbientColor;

uniform float SpecularKs;
uniform vec4 Specular;
uniform float SpecularExpo;

uniform float DiffuseKd;
uniform vec4 Diffuse;

uniform vec4 LightSourcePosition;
uniform vec4 LightSourceColor;

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
    
    
    vec4 spec = Specular*SpecularKs*pow(max(dot(R,V), 0),SpecularExpo);
    vec4 diffusion  = Diffuse*DiffuseKd*dot(L,N);
    vec4 ambience = Ambient*AmbientKa*AmbientColor;
    
    fragmentColor = ambience+LightSourceColor*(diffusion+spec); //vec4( 1.0, 1.0, 1.0, 1.0 );
}
