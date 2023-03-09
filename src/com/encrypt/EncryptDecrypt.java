package com.encrypt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptDecrypt {

	public byte[] encrypt(String data) {
		byte[] bytes = data.getBytes();
		int[] intArray = new int[] {-3,1,5,-4};
		byte[] recusion = recusionEncrypt(bytes,intArray,0,0);

		return Base64.getEncoder().encode(recusion);
	}

	private byte[] recusionEncrypt(byte[] bytes, int[] intArray, int i, int j) {
		if(i >= bytes.length) {
			i = 0;
			j++;
		}
		if(j >= intArray.length) {
			return bytes;
		}
		byte byteValue = (byte) (bytes[i] + i + intArray[j]);
		bytes[i] = byteValue;
		return recusionEncrypt(bytes, intArray, i + 1, j);
	}

	public String decrypt(byte[] decode) {
		decode = Base64.getDecoder().decode(decode);
		byte[] newDecode = decode;
		int[] intArray = new int[] {-3 , 1 , 5 , -4};
		byte[] recursionDecrypt = recursionDecrypt(newDecode , intArray , 0 , 0);

		return new String(recursionDecrypt, StandardCharsets.UTF_8);

	}

	private byte[] recursionDecrypt(byte[] newDecode, int[] intArray, int i, int j) {
		if(i >= newDecode.length) {
			i = 0;
			j++;
		}
		if(j >= intArray.length) {
			return newDecode;
		}
		byte byteValue = (byte) (newDecode[i] - i - (intArray[j]));
		newDecode[i] = byteValue;
		return recursionDecrypt(newDecode, intArray, i + 1, j);

	}

	public static void main(String[] args) {
		EncryptDecrypt decrypt = new EncryptDecrypt();
		byte[] encrypt = decrypt.encrypt("Raja");
		
		String string = new String(encrypt, StandardCharsets.UTF_8);
		
		System.out.println(string);
		
		String decrypt2 = decrypt.decrypt(string.getBytes());
		System.out.println(decrypt2);
	}
}
