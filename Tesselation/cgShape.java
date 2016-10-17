

import java.awt.*;
import java.nio.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;

public class cgShape extends simpleShape
{
	public static float half;
	public static double PI;
	public static float y0, y1, x0, z0, x1,z1;
	float[] teapotVertices = {
	         0.098626f,  0.499861f, -0.285539f,  0.098626f,  0.499861f, -0.166305f,
	         0.103349f,  0.495678f, -0.164634f, -0.014442f,  0.499861f, -0.399697f,
	        -0.125276f,  0.499861f, -0.399697f, -0.124885f,  0.495678f, -0.398472f,
	        -0.233668f,  0.495678f, -0.283884f, -0.233668f,  0.495678f, -0.167959f,
	        -0.235994f,  0.487311f, -0.167137f, -0.243066f,  0.495678f, -0.287210f,
	        -0.238344f,  0.499861f, -0.285539f, -0.126456f,  0.499861f, -0.403397f,
	        -0.014832f,  0.495678f, -0.053372f, -0.014442f,  0.499861f, -0.052147f,
	         0.095114f,  0.499861f, -0.167548f, -0.234831f,  0.499861f, -0.284296f,
	        -0.234831f,  0.499861f, -0.167548f, -0.014832f,  0.495678f, -0.398472f,
	        -0.125666f,  0.487311f, -0.400922f, -0.013262f,  0.499861f, -0.048447f,
	        -0.126456f,  0.499861f, -0.048447f, -0.128042f,  0.495678f, -0.043472f,
	         0.096277f,  0.487311f, -0.284707f,  0.096277f,  0.487311f, -0.167137f,
	         0.093951f,  0.495678f, -0.167959f, -0.011675f,  0.495678f, -0.043472f,
	        -0.235994f,  0.487311f, -0.284707f, -0.011675f,  0.495678f, -0.408372f,
	        -0.128042f,  0.495678f, -0.408372f,  0.070712f,  0.499861f, -0.334590f,
	         0.073705f,  0.499861f, -0.336903f, -0.014051f,  0.487311f, -0.400922f,
	        -0.243066f,  0.495678f, -0.164634f,  0.095114f,  0.499861f, -0.284296f,
	         0.093951f,  0.495678f, -0.283884f, -0.124885f,  0.495678f, -0.053372f,
	        -0.125276f,  0.499861f, -0.052147f, -0.014051f,  0.487311f, -0.050922f,
	         0.007794f,  0.255758f,  0.017578f,  0.127115f,  0.255758f, -0.073652f,
	         0.009866f,  0.288108f,  0.024078f, -0.238344f,  0.499861f, -0.166305f,
	        -0.125666f,  0.487311f, -0.050922f,  0.009866f,  0.288108f, -0.475922f,
	        -0.149584f,  0.288108f, -0.475922f, -0.307195f,  0.288108f, -0.309901f,
	        -0.268472f,  0.440618f, -0.244874f, -0.268472f,  0.440618f, -0.206970f,
	        -0.405045f,  0.423728f, -0.206970f, -0.033205f,  0.582610f, -0.212917f,
	        -0.046902f,  0.582610f, -0.193002f, -0.043216f,  0.571614f, -0.187716f,
	         0.103349f,  0.495678f, -0.287210f,  0.167477f,  0.288108f, -0.309901f,
	        -0.149584f,  0.288108f,  0.024078f, -0.147511f,  0.255758f,  0.017578f,
	        -0.129653f,  0.188507f, -0.038422f, -0.069859f,  0.188507f, -0.028504f,
	         0.167477f,  0.288108f, -0.141943f,  0.161307f,  0.255758f, -0.144126f,
	        -0.318450f,  0.283965f, -0.206970f, -0.379458f,  0.331574f, -0.206970f,
	        -0.315351f,  0.274084f, -0.197494f, -0.147511f,  0.255758f, -0.469422f,
	        -0.266832f,  0.255758f, -0.378191f,  0.007794f,  0.255758f, -0.469422f,
	        -0.077011f,  0.541016f, -0.203519f, -0.087985f,  0.541016f, -0.211903f,
	        -0.077831f,  0.527152f, -0.200922f,  0.081815f,  0.188507f, -0.108671f,
	         0.161307f,  0.255758f, -0.307718f,  0.074698f,  0.255758f, -0.433405f,
	         0.041453f,  0.188507f, -0.385689f,  0.040028f,  0.182610f, -0.383644f,
	        -0.069859f,  0.188507f, -0.423340f, -0.258576f,  0.464283f, -0.244874f,
	        -0.411516f,  0.452046f, -0.244874f, -0.411516f,  0.452046f, -0.206970f,
	        -0.221532f,  0.188507f, -0.343172f, -0.307195f,  0.288108f, -0.141943f,
	        -0.301024f,  0.255758f, -0.144126f, -0.424567f,  0.431661f, -0.197494f,
	        -0.433466f,  0.435277f, -0.206970f, -0.440796f,  0.407630f, -0.206970f,
	        -0.433466f,  0.435277f, -0.244874f, -0.424567f,  0.431661f, -0.254350f,
	        -0.420405f,  0.407630f, -0.254350f, -0.431500f,  0.407630f, -0.254350f,
	        -0.413945f,  0.427344f, -0.197494f, -0.420405f,  0.407630f, -0.197494f,
	         0.290167f,  0.447480f, -0.244409f,  0.295282f,  0.446196f, -0.225922f,
	         0.290167f,  0.447480f, -0.207435f, -0.413945f,  0.427344f, -0.254350f,
	        -0.405045f,  0.423728f, -0.244874f, -0.265373f,  0.448029f, -0.197494f,
	        -0.431500f,  0.407630f, -0.197494f, -0.440796f,  0.407630f, -0.244874f,
	        -0.247861f,  0.188507f, -0.162938f, -0.435727f,  0.373886f, -0.244874f,
	        -0.435727f,  0.373886f, -0.206970f, -0.308554f,  0.252411f, -0.206970f,
	        -0.311653f,  0.262291f, -0.197494f, -0.061886f,  0.527152f, -0.200922f,
	        -0.062706f,  0.541016f, -0.203519f, -0.212830f,  0.497192f, -0.276511f,
	        -0.212830f,  0.497192f, -0.175333f, -0.093592f,  0.527152f, -0.234320f,
	        -0.318450f,  0.283965f, -0.244874f, -0.315351f,  0.274084f, -0.254350f,
	        -0.379458f,  0.331574f, -0.244874f, -0.319749f,  0.288108f, -0.225922f,
	         0.346618f,  0.493377f, -0.210785f,  0.217033f,  0.288937f, -0.186921f,
	         0.142548f,  0.259662f, -0.184227f,  0.142548f,  0.248268f, -0.225922f,
	         0.326737f,  0.493022f, -0.203217f,  0.210637f,  0.310674f, -0.167420f,
	         0.142548f,  0.286833f, -0.163380f, -0.092815f,  0.582610f, -0.258842f,
	        -0.106513f,  0.582610f, -0.238927f, -0.057512f,  0.582610f, -0.264532f,
	         0.210637f,  0.310674f, -0.284423f,  0.142549f,  0.286833f, -0.288464f,
	         0.142548f,  0.319264f, -0.288464f,  0.196608f,  0.358355f, -0.264923f,
	         0.275290f,  0.487311f, -0.241715f,  0.303009f,  0.492597f, -0.248627f,
	         0.193926f,  0.367471f, -0.225922f,  0.267494f,  0.487311f, -0.225922f,
	         0.142548f,  0.357829f, -0.225922f,  0.142548f,  0.346435f, -0.184227f,
	        -0.055530f,  0.571614f, -0.270733f, -0.062706f,  0.541016f, -0.248324f,
	        -0.077011f,  0.541016f, -0.248324f,  0.142548f,  0.346435f, -0.267617f,
	         0.351059f,  0.496732f, -0.212352f,  0.359204f,  0.496993f, -0.225922f,
	         0.348533f,  0.497053f, -0.214222f, -0.308554f,  0.252411f, -0.244874f,
	         0.073113f,  0.497192f, -0.175333f, -0.046125f,  0.527152f, -0.234320f,
	        -0.046125f,  0.527152f, -0.217524f,  0.142548f,  0.259662f, -0.267617f,
	         0.346618f,  0.493377f, -0.241058f,  0.217033f,  0.288937f, -0.264923f,
	        -0.112400f,  0.571614f, -0.241015f, -0.106130f,  0.571614f, -0.197858f,
	         0.312374f,  0.487311f, -0.211708f,  0.306254f,  0.492850f, -0.210722f,
	         0.309708f,  0.495492f, -0.208372f,  0.297581f,  0.487311f, -0.211708f,
	         0.331636f,  0.496109f, -0.205567f,  0.303009f,  0.492597f, -0.203217f,
	         0.285186f,  0.487311f, -0.216446f,  0.291059f,  0.492316f, -0.215789f,
	         0.292009f,  0.494781f, -0.214222f,  0.196608f,  0.358355f, -0.186921f,
	         0.275290f,  0.487311f, -0.210128f,  0.279988f,  0.487311f, -0.225922f,
	         0.284686f,  0.492092f, -0.225922f,  0.284586f,  0.494483f, -0.225922f,
	         0.291059f,  0.492316f, -0.236055f,  0.292009f,  0.494781f, -0.237622f,
	         0.306254f,  0.492850f, -0.241122f,  0.285186f,  0.487311f, -0.235398f,
	         0.309708f,  0.495492f, -0.243472f,  0.312374f,  0.487311f, -0.240136f,
	         0.297581f,  0.487311f, -0.240136f, -0.301024f,  0.255758f, -0.307718f,
	         0.331636f,  0.496109f, -0.246276f,  0.326738f,  0.493022f, -0.248627f,
	         0.324769f,  0.487311f, -0.235398f,  0.351059f,  0.496732f, -0.239492f,
	        -0.061886f,  0.527152f, -0.250922f, -0.077831f,  0.527152f, -0.250922f,
	        -0.411109f,  0.407630f, -0.206970f, -0.096501f,  0.571614f, -0.264128f,
	        -0.091126f,  0.541016f, -0.233456f, -0.083167f,  0.541016f, -0.245016f,
	        -0.056550f,  0.541016f, -0.206828f, -0.093592f,  0.527152f, -0.217524f,
	        -0.091126f,  0.541016f, -0.218388f, -0.027318f,  0.571614f, -0.210829f,
	        -0.033588f,  0.571614f, -0.253986f, -0.051732f,  0.541016f, -0.239940f,
	        -0.048591f,  0.541016f, -0.233456f, -0.048591f,  0.541016f, -0.218388f,
	        -0.082205f,  0.582610f, -0.187312f, -0.084187f,  0.571614f, -0.181111f,
	        -0.038606f,  0.582610f, -0.250104f, -0.101111f,  0.582610f, -0.201740f,
	        -0.021832f,  0.497192f, -0.376522f, -0.018037f,  0.487311f, -0.388422f,
	        -0.121680f,  0.487311f, -0.388422f, -0.013262f,  0.499861f, -0.403397f,
	         0.073113f,  0.497192f, -0.276511f,  0.084410f,  0.487311f, -0.171335f,
	        -0.224127f,  0.487311f, -0.280508f, -0.224127f,  0.487311f, -0.171335f,
	        -0.121680f,  0.487311f, -0.063422f, -0.018037f,  0.487311f, -0.063422f,
	        -0.021832f,  0.497192f, -0.075322f,  0.084410f,  0.487311f, -0.280508f,
	         0.142549f,  0.319264f, -0.163380f, -0.117885f,  0.497192f, -0.376522f,
	         0.105865f,  0.182610f, -0.288100f,  0.108143f,  0.188507f, -0.288906f,
	         0.117559f,  0.188507f, -0.225922f, -0.117885f,  0.497192f, -0.075322f,
	        -0.129653f,  0.188507f, -0.038422f, -0.128887f,  0.182610f, -0.040822f,
	        -0.257277f,  0.188507f, -0.225922f, -0.247861f,  0.188507f, -0.162938f,
	        -0.010830f,  0.182610f, -0.040822f, -0.245582f,  0.182610f, -0.163744f,
	        -0.219591f,  0.182610f, -0.341672f, -0.010065f,  0.188507f, -0.038422f,
	        -0.010065f,  0.188507f, -0.038422f,  0.079874f,  0.182610f, -0.110172f,
	        -0.129653f,  0.188507f, -0.413422f, -0.128887f,  0.182610f, -0.411022f,
	        -0.258576f,  0.464283f, -0.206970f, -0.129653f,  0.188507f, -0.413422f,
	        -0.261675f,  0.456873f, -0.254350f,  0.108143f,  0.188507f, -0.288906f,
	        -0.265373f,  0.448029f, -0.254350f, -0.311653f,  0.262291f, -0.254350f,
	         0.219715f,  0.279821f, -0.225922f, -0.411109f,  0.407630f, -0.244874f,
	        -0.261675f,  0.456873f, -0.197494f,  0.330833f,  0.496342f, -0.208372f,
	         0.324769f,  0.487311f, -0.216446f
	    };
	
	int[] teapotFaces = {
	          0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,
	         12,  13,  14,   5,   4,  15,  15,  16,   7,  17,   5,  18,
	         19,  20,  21,  22,  23,  24,   1,  19,  25,  26,  18,   5,
	         27,  28,  11,   3,  29,  30,  17,  31,  22,  10,   9,  32,
	         33,  34,  24,  12,  35,  36,  23,  37,  12,  38,  39,  40,
	         20,  13,  36,  20,  41,  32,  35,  42,   8,  35,   7,  16,
	          2,  25,  40,  42,  35,  12,  28,  27,  43,  28,  44,  45,
	         46,  47,  48,  49,  50,  51,  52,  53,  43,  34,  33,  29,
	         25,  21,  54,  55,  56,  57,  53,  58,  59,  60,  61,  62,
	         63,  64,  44,  65,  63,  44,  54,  55,  38,  66,  67,  68,
	         69,  39,  38,  70,  71,  53,  72,  73,  74,  65,  71,  72,
	         75,  76,  77,  78,  64,  63,  54,  79,  80,  59,  39,  69,
	         81,  82,  83,  84,  76,  85,  86,  87,  85,  48,  88,  89,
	         90,  91,  92,  86,  93,  94,  47,  95,  88,  89,  81,  96,
	         97,  84,  85,  98,  56,  55,  97,  99, 100, 101, 102, 100,
	         68, 103, 104, 105, 106, 107,  46,  94,  93, 108, 109, 110,
	         61,  60, 111, 112,  92,  91, 113, 114, 115,  92, 112, 116,
	        117, 118, 114, 119, 120, 121, 122, 123, 124,  82,  81,  77,
	        125, 126, 127, 125, 128, 129, 128, 130, 131, 132, 133, 134,
	        135, 130, 128,  76,  84,  82, 136, 137, 138,  99, 139, 101,
	        140, 141, 142, 143, 123, 122,  91,  90, 144, 115, 143, 145,
	        120, 146, 147, 148, 149, 150, 116, 112, 136, 151, 149, 148,
	        152, 153, 116, 154, 155, 149, 155, 156, 150, 157, 158, 129,
	        159, 160, 155, 160, 161, 156, 162, 160, 159, 163, 161, 160,
	        164, 162, 165, 166, 163, 162,  79,  54,  21, 167, 164, 168,
	        111,  45, 169, 170, 171, 127, 164, 167, 172, 173, 144, 171,
	        133, 174, 175, 176,  61, 110, 120, 119, 177, 178, 179, 107,
	        104, 180,  51, 107, 181, 182,   2,  58,  53,  49, 183, 184,
	        185, 186, 141, 184, 185, 133, 186, 187, 142,  72,  71,  70,
	        146, 178, 182, 188, 189,  51, 121, 190, 184, 147,  67,  66,
	         51, 180, 187, 186, 185, 184, 121, 132, 177, 188, 191, 147,
	        187, 180, 142, 177, 179, 178, 192, 193, 194, 195,  30,  27,
	        196, 140, 197, 105, 198, 199, 200, 201, 202, 192, 196, 203,
	        197, 140, 202, 204, 118, 117, 198, 105, 205,   9,  45, 111,
	        206, 207, 208, 209, 106, 199, 158, 157, 153,  94,  48, 176,
	        210, 211,  57, 144,  90, 171, 126, 129, 161, 212, 213,  80,
	         99,  97,  87, 214, 211, 215,  78, 216, 212,  52,   0,   2,
	         17,   3,   5,  26,   6,   8,  28,   9,  11,  24,  12,  14,
	          6,   5,  15,   6,  15,   7,  31,  17,  18,  25,  19,  21,
	         34,  22,  24,   2,   1,  25,   6,  26,   5, 195,  27,  11,
	        195,   3,  30,  34,  17,  22,  41,  10,  32,  14,  33,  24,
	         13,  12,  36,  24,  23,  12,  39,  59,  58, 195,  11,   3,
	          0,  30,  29,  39,  58,  40,   7,  35,   8,   0,  29,  33,
	         15,   4,  11,   1,   0,  33,  15,  11,  10,   1,  33,  14,
	         10,  41,  15,  19,   1,  14,  11,   4,   3,  19,  14,  13,
	         36,  16,  41,  20,  19,  13,  36,  41,  20,  21,  20,  32,
	         41,  16,  15,  36,  35,  16,  58,   2,  40,  37,  42,  12,
	         44,  28,  43,   9,  28,  45,  94,  46,  48, 183,  49,  51,
	         27,  52,  43,  34,  29,  17,  40,  25,  54,  55,  57,  38,
	         29,   3,  17,  70,  53,  59,  61, 176,  89,  57, 217,  38,
	         64, 169,  45,  43,  65,  44,  61,  89,  62,  40,  54,  38,
	         64,  45,  44,  67, 182, 181, 217, 218, 214,  69, 217, 219,
	         67, 181,  68,  71,  65,  43, 220,  74, 221,  69,  38, 217,
	        217, 214, 219,  65,  74,  63,  71,  43,  53,  74,  73, 221,
	        222,  75,  77, 223, 220, 221,  65,  72,  74,  74, 223,  63,
	         78, 223, 216,  55,  54,  80,  59, 208,  70,  78,  63, 223,
	        223, 221, 216,  96,  81,  83,  76,  75, 224,  59,  69, 208,
	        208, 225,  70, 226,  93, 224, 227,  87, 109,  76, 224,  85,
	         92, 113, 228,  93,  85, 224,  93,  86,  85,  87,  86, 109,
	        176,  48,  89, 228, 145,  90, 229,  86,  94, 228,  90,  92,
	         48,  47,  88,  88,  95, 230,  96, 102,  62,  88, 230,  81,
	         96,  62,  89,  89,  88,  81,  87,  97,  85,  80, 213,  98,
	        211,  56, 215, 211, 210,  56,  80,  98,  55,  83, 100,  96,
	         82,  84,  97,  56,  98, 215,  82,  97,  83,  66,  68, 104,
	         97, 100,  83, 100, 102,  96, 175, 174, 192, 105, 107, 205,
	        106, 181, 107, 181, 106,  68, 192, 205, 175, 106, 209,  68,
	        205, 107, 175, 226,  46,  93, 229, 110,  86,  61, 111, 110,
	        110, 109,  86, 113,  92, 117, 137, 112,  91, 111, 108, 110,
	        228, 113, 115, 137, 136, 112, 113, 117, 114, 190, 121,  49,
	         92, 116, 117, 222,  77, 230, 191, 188, 120,  50,  49, 188,
	        127, 171, 122,  49, 121, 188, 120, 188, 121, 122, 124, 127,
	        124, 135, 125,  77,  81, 230, 126, 125, 129, 124, 125, 127,
	        157, 128, 131, 132, 134, 177, 125, 135, 128,  77,  76,  82,
	        134, 179, 177, 138, 231, 152, 100,  99, 101, 152, 136, 138,
	        103,  68, 209, 140, 142, 202,  91, 144, 137, 141, 196, 174,
	        209, 202, 103, 196, 192, 174, 202, 142, 103, 145, 143, 122,
	        141, 140, 196, 228, 115, 145, 191, 120, 147, 144, 173, 137,
	        232, 148, 150, 151, 154, 149, 152, 116, 136, 231, 138, 232,
	        231, 232, 150, 149, 155, 150, 128, 157, 129, 154, 159, 155,
	        155, 160, 156, 165, 162, 159, 162, 163, 160, 168, 164, 165,
	        164, 166, 162,  32,  79,  21, 170, 173, 171,  80, 111, 169,
	        166, 164, 172,  80,  79, 111, 134, 133, 175, 229, 176, 110,
	        146, 120, 177, 179, 134, 175,  66, 104, 189, 178, 107, 182,
	        179, 175, 107,  52,   2,  53, 104,  51, 189, 190,  49, 184,
	        174, 133, 185, 132, 184, 133, 185, 141, 174, 141, 186, 142,
	        225, 207, 206,  72, 225,  73, 146, 182, 147,  50, 188,  51,
	         72,  70, 225, 225, 206,  73, 132, 121, 184, 182,  67, 147,
	        189, 147,  66, 183,  51, 187, 187, 186, 183, 119, 121, 177,
	        189, 188, 147, 186, 184, 183, 180, 104, 103, 146, 177, 178,
	        205, 192, 194, 180, 103, 142,   0,  52,  30, 203, 196, 197,
	         30,  52,  27, 106, 105, 199, 209, 200, 202, 193, 192, 203,
	        201, 197, 202, 204, 117, 153, 194, 198, 205,   9, 111,  32,
	        117, 116, 153, 206, 208, 219, 200, 209, 199, 111,  79,  32,
	        157, 131, 204, 208,  69, 219, 229,  94, 176, 218,  57, 214,
	        157, 204, 153,  90, 145, 122,  57, 211, 214, 172, 232, 138,
	        138, 137, 173,  90, 122, 171, 127, 166, 170, 150, 153, 152,
	        153, 156, 158, 231, 150, 152, 172, 138, 173, 212, 169,  78,
	        173, 170, 172, 153, 150, 156, 170, 166, 172, 158, 156, 161,
	        163, 166, 127, 129, 158, 161, 227,  99,  87, 163, 126, 161,
	        163, 127, 126, 221,  73, 206, 169,  64,  78, 212,  80, 169,
	        215, 216, 221, 227, 139,  99, 206, 219, 214,  98, 212, 215,
	        215, 221, 206, 206, 214, 215, 212, 216, 215
	    };

    ///
    // constructor
    ///
    public cgShape()
    {
		half = 0.5f;	//value for the axis that is constant
		PI = Math.PI;
    }

    ///
    // makeCube - Create a unit cube, centered at the origin, with a given
    // number of subdivisions in each direction on each face.
    //
    // @param subdivision - number of equal subdivisons to be made in each
    //        direction along each face
    //
    // Can only use calls to addTriangle()
    ///
    public void makeCube (int subdivisions)
    {
        if( subdivisions < 1 )
            subdivisions = 1;
       
        if(subdivisions >30){
        	subdivisions = 29;
        	System.out.println("Tesselations canot exceed 30");
        }
        float divisions = 1/(float)subdivisions;
          
        for(int i =0;i<subdivisions;i++){
        	x0 = (float)i*divisions-half;
        	x1 = (float)(i+1)*divisions-half;
        	for(int j=0; j<subdivisions;j++){
        		 y0 = (float)j*divisions-half;
    			 y1 = (float)(j+1)*divisions-half;
    			
    			 System.out.println(x0+" "+x1+" "+y0+" "+y1);
        		addTriangle(x1,y0, -half, x0, y0, -half, x0, y1, -half);
        		addTriangle(x0, y0, half, x1, y0, half, x0, y1, half);
        		addTriangle(-half, x0, y1, -half, x1,y0, -half, x0, y0);
        		addTriangle(half, x1, y0,half, x0, y1, half, x0, y0);
        		addTriangle(x1, -half, y0, x0, -half, y1, x0, -half, y0);
        		addTriangle(x0, half, y1, x1, half, y0, x0, half,y0);
        			
        		addTriangle(x1,y0, -half, x0, y1, -half, x1, y1, -half);
    			addTriangle(x0, y1, half,x1, y0, half, x1, y1, half);
    			addTriangle(-half, x1, y1, -half,x1, y0, -half, x0, y1);
    			addTriangle(half, x1, y0, half, x1, y1, half, x0, y1);
    			addTriangle(x1, -half, y0, x1,-half, y1, x0, -half, y1);
    			addTriangle(x1, half, y1,x1, half, y0, x0,half, y1);
    			
        	}
        }

        // YOUR IMPLEMENTATION HERE
    }

    ///
    // makeCylinder - Create polygons for a cylinder with unit height, centered
    // at the origin, with separate number of radial subdivisions and height
    // subdivisions.
    //
    // @param radius - Radius of the base of the cylinder
    // @param radialDivision - number of subdivisions on the radial base
    // @param heightDivisions - number of subdivisions along the height
    //
    // Can only use calls to addTriangle()
    ///
    public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
    {
        if( radialDivisions < 3 )
            radialDivisions = 3;

        if( heightDivisions < 1 )
            heightDivisions = 1;

        // YOUR IMPLEMENTATION HERE
        
    	for (int i=0;i<radialDivisions;i++) {
    		x0 = (float) (radius*Math.cos((float)i*2*PI/(float)radialDivisions));
    		x1 = (float) (radius*Math.cos((float)(i+1)*2*PI/(float)radialDivisions));
    		z0 = (float) (radius*Math.sin((float)i*2*PI/(float)radialDivisions));	
    		z1 = (float) (radius*Math.sin((float)(i+1)*2*PI/(float)radialDivisions));
    		
    		//Add bottom and top triangle
    		addTriangle(x1,half,z1,x0,half,z0,0.0f,half,0.0f);
    		addTriangle(0.0f,-half,0.0f,x0,-half,z0,x1,-half,z1); 
    			
    		for (int j=0;j<heightDivisions;j++) {
    			y0 = (float)((j)/(float)heightDivisions-half);
    			y1 = (float)((j + 1)/(float)heightDivisions-half);
    			//add polygon in between
    			addTriangle(x0,y0,z0,x0,y1,z0,x1,y1,z1);
    			addTriangle(x0,y0,z0,x1,y1,z1,x1,y0,z1);
    		}
    	}
    }

    ///
    // makeCone - Create polygons for a cone with unit height, centered at the
    // origin, with separate number of radial subdivisions and height
    // subdivisions.
    //
    // @param radius - Radius of the base of the cone
    // @param radialDivision - number of subdivisions on the radial base
    // @param heightDivisions - number of subdivisions along the height
    //
    // Can only use calls to addTriangle()
    ///
    public void makeCone (float radius, int radialDivisions, int heightDivisions)
    {
        if( radialDivisions < 3 )
            radialDivisions = 3;

        if( heightDivisions < 1 )
            heightDivisions = 2;

        float diffx0,diffz1,diffx1,diffz0;
		
        // YOUR IMPLEMENTATION HERE
        

    	for (int i=0; i<radialDivisions; i++) {
    		 x0 =  (float) (radius*Math.cos(i*2*PI/(float)radialDivisions));
    		 z0 = (float) (radius*Math.sin(i*2*PI/ (float)radialDivisions));
    		 x1 = (float) (radius*Math.cos((i+1)*2*PI/(float)radialDivisions));
    		 z1 = (float) (radius*Math.sin((i+1)*2*PI/(float)radialDivisions));
    		 //base triangle
    		addTriangle(x0, -half, z0, x1, -half, z1, 0f, -half, 0f);

    		 
    		diffx0 =-x0 /(float)heightDivisions;
    		diffx1 =-x1 /(float)heightDivisions;
    		
    		y0 = -half;
    		y1 =(1/(float)heightDivisions);
   		
    		diffz0  =-z0/(float)heightDivisions;
    		diffz1 =-z1/(float)heightDivisions;
    		
    		//polgon in between
    		for (int j = 0;j < heightDivisions;j++) {			
    			addTriangle(x0,y0,z0,x0+diffx0, y0+y1,z0+diffz0,x1,y0,z1);
    			addTriangle(x0+diffx0,y0+y1,z0+diffz0,x1+diffx1,y0+y1,z1+diffz1,x1,y0,z1);
    			x0 +=diffx0;
    			x1 += diffx1;
    			
    			y0 +=y1;
    			
    			z0 += diffz0;
    			z1 +=diffz1;
    			
    		}
    		//top cone
    		addTriangle(x0,y0,z0,0f,half,0f,x1,y0,z1);
    		}   
    }

   

	///
    // makeSphere - Create sphere of a given radius, centered at the origin,
    // using spherical coordinates with separate number of thetha and
    // phi subdivisions.
    //
    // @param radius - Radius of the sphere
    // @param slides - number of subdivisions in the theta direction
    // @param stacks - Number of subdivisions in the phi direction.
    //
    // Can only use calls to addTriangle
    ///
    public void makeSphere (float radius, int slices, int stacks)
    {
	// IF USING RECURSIVE SUBDIVISION, MODIFY THIS TO USE
	// A MINIMUM OF 1 AND A MAXIMUM OF 5 FOR 'slices'

        if( slices < 3 )
            slices = 3;

        if( stacks < 3 )
            stacks = 3;
        
    	ArrayList<Triangle> points =  new ArrayList<Triangle>();
    	addPoints((int)radius,points);
    	float x1,x2,x3,y1,y2,y3,z1,z2,z3;
    	for(int i=0;i<points.size();i++){
    		x1 =points.get(i).x1;
    		x2 =points.get(i).x2;
    		x3 =points.get(i).x3;
    		y1 =points.get(i).y1;
    		y2 =points.get(i).y2;
    		y3 =points.get(i).y3;
    		z1 =points.get(i).z1;
    		z2 =points.get(i).z2;
    		z3 =points.get(i).z3;
    		formTriangle(x1, y1, z1, x2, y2, z2, x3, y3, z3, radius,slices);
    		
    	}
        
    		
        	}   	
    	 public void addPoints(float radius, ArrayList<Triangle> points){
    		 points.add(new Triangle(0f, radius, -1f, -radius, 1f, 0f, radius, 1f, 0f));
    	    	points.add(new Triangle(0f, radius, 1f, radius, 1f, 0f, -radius, 1f, 0f));
    	    	points.add(new Triangle(0f, radius, 1f, -1f, 0f, radius, 0f, -radius, 1f));
    	    	points.add(new Triangle(0f, radius, 1f, 0f, -radius, 1f, 1f, 0f, radius));
    	    	points.add(new Triangle(0f, radius, -1f, 1f, 0f, -radius, 0f, -radius, -1f));
    	    	points.add(new Triangle(0f, radius, -1f, 0f, -radius, -1f, -1f, 0f, -radius));
    	    	points.add(new Triangle(0f, -radius, 1f, -radius, -1f, 0f, radius, -1f, 0f));
    	       	points.add(new Triangle(0f, -radius, -1f, radius, -1f, 0f, -radius, -1f, 0f));
    	    	points.add(new Triangle(-radius, 1f, 0f, -1f, 0f, -radius, -1f, 0f, radius));
    	       	points.add(new Triangle(-radius, -1f, 0f, -1f, 0f, radius, -1f, 0f, -radius));
    	       	points.add(new Triangle(radius, 1f, 0f, 1f, 0f, radius, 1f, 0f, -radius));
    	    	points.add(new Triangle(radius, -1f, 0f, 1f, 0f, -radius, 1f, 0f, radius));
    	    	points.add(new Triangle(0f, radius, 1f, -radius, 1f, 0f, -1f, 0f, radius));
    	    	points.add(new Triangle(0f, radius, 1f, 1f, 0f, radius, radius, 1f, 0f));
    	    	points.add(new Triangle(0f, radius, -1f, -1f, 0f, -radius, -radius, 1f, 0f));
    	    	points.add(new Triangle(0f, radius, -1f, radius, 1f, 0f, 1f, 0f, -radius));
    	    	points.add(new Triangle(0f, -radius, -1f, -radius, -1f, 0f, -1f, 0f, -radius));
    	    	points.add(new Triangle(0f, -radius, -1f, 1f, 0f, -radius, radius, -1f, 0f));
    	    	points.add(new Triangle(0f, -radius, 1f, -1f, 0f, radius, -radius, -1f, 0f));
    	    	points.add(new Triangle(0f, -radius, 1f, radius, -1f, 0f, 1f, 0f, radius));
    		
    	 }
    
    	 public void formTriangle(float x1,float y1,float z1,
				 				  float x2,float y2,float z2,
				 				  float x3,float y3,float z3,
				 				  float radius,int slices){
    		 	float x12, x23,x31,y12,y23,y31,z12,z23,z31,normalize;
 
    		 	if(slices ==1){	// when no. of slices is 1 draw the triangle

    		 		//get normalize and the final points to form triangle
    		 		normalize = getNormalize(x1,y1,z1);
    		 		x1 =(float)(x1*radius/(float)normalize);
    		 		y1 =(float)(y1*radius/(float)normalize);
    		 		z1 =(float)(z1*radius/(float)normalize);

    		 		normalize = getNormalize(x2,y2,z2);
    		 		x2 =(float)(x2*radius/(float)normalize);
    		 		y2 =(float)(y2*radius/(float)normalize);
    		 		z2 =(float)(z2*radius/(float)normalize);

    		 		normalize = getNormalize(x3,y3,z3);
    		 		x3 =(float)(x3*radius/(float)normalize);
    		 		y3 =(float)(y3*radius/(float)normalize);
    		 		z3 =(float)(z3*radius/(float)normalize);

    		 		addTriangle(x1,y1,z1,x2,y2,z2,x3,y3,z3);
	
    		 	}else{	// when slices are more than 1 keep dividing triangle into 
    		 			// smaller triangle

    		 		// get midpoint to form triangles between them		
    		 		x12 = getMidPoint(x1,x2);
    		 		x23 = getMidPoint(x3,x2);
    		 		x31 = getMidPoint(x1,x3);
    		 		y12 = getMidPoint(y1,y2);
    		 		y23 = getMidPoint(y3,y2);
    		 		y31 = getMidPoint(y1,y3);
    		 		z12 = getMidPoint(z1,z2);
    		 		z23 = getMidPoint(z3,z2);
    		 		z31 = getMidPoint(z1,z3);

    		 		// recursive call till number of slices is minimum
    		 		formTriangle(x1, y1, z1, x12, y12, z12, x31, y31, z31, radius,slices-1);
    		 		formTriangle(x12, y12, z12, x23, y23, z23, x31, y31, z31,radius,slices-1);
    		 		formTriangle(x12, y12, z12, x2, y2, z2, x23, y23, z23, radius,slices-1);
    		 		formTriangle(x31, y31, z31, x23, y23, z23, x3, y3, z3, radius,slices-1);
    		 	}
    	 }

    	 /*Function to get mid point of given points
    	  * params: float a: point a
    	  * param: float b: point b
    	  * return: float: midpoint of the 2 points
    	  */
    	 public float getMidPoint(float a, float b){
    		 return (float)(a+b)/(float)2;    	
    	 }

    	 /*Function to get normalization of given point
    	  * params: float a: point a
    	  * param: float b: point b
    	  * param: float c: point c
    	  * return: float: normalization of the 3 points
    	  */
    	 public float getNormalize(float a,float b,float c){
    		 return (float)Math.sqrt((a*a)+(b*b)+(c*c));
    	 }
    	 
    	 public void makeTeapot() {

    	        //
    	        // Approach:  Each group of three values in teapotVertices
    	        // represents a vertex.  Indices into that array are in
    	        // teapotFaces.  For vertex N, the relevant entries in
    	        // teapotVertices are 3N+0, 3N+1, and 3N+2; that is,
    	        //
    	        //   vertex   0:  slots 0, 1, 2
    	        //   vertex   1:  slots 3, 4, 5
    	        //   ...
    	        //   vertex 232:  slots 696, 697, 698
    	        //
    	        // Iterate through teapotFaces in groups of three entries;
    	        // for each index, calculate the corresponding entry indices
    	        // into the teapotVertices array.
    	        //

    	        for( int i = 0; i < teapotFaces.length-2 ; i += 3 ) {
    	            // calculate the base indices of the three vertices
    	            int point1 = 3 * teapotFaces[i];    // slots 0, 1, 2
    	            int point2 = 3 * teapotFaces[i+1];  // slots 3, 4, 5
    	            int point3 = 3 * teapotFaces[i+2];  // slots 6, 7, 8

    	            addTriangle( teapotVertices[point1+0],
    	                         teapotVertices[point1+1] - 0.5f,
    	                         teapotVertices[point1+2] - 1.0f,
    	                         teapotVertices[point2+0],
    	                         teapotVertices[point2+1] - 0.5f,
    	                         teapotVertices[point2+2] - 1.0f,
    	                         teapotVertices[point3+0],
    	                         teapotVertices[point3+1] - 0.5f,
    	                         teapotVertices[point3+2] - 1.0f
    	                       );

    	        }
    	    }

}
