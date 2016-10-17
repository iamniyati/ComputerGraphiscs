//
// Vertex shader for the transformation assignment
//
// Author:  W. R. Carithers
//
// Contributor:  YOUR_NAME_HERE

#version 150

// attribute:  vertex position
in vec4 vPosition;

// add other global shader variables to hold the
// parameters your application code is providing

void main()
{
    // By default, no transformations are performed.
    gl_Position =  vPosition;
}
