package com.seamfix.nimc.maybeach.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class Sha512ImplTest {
	
	
	@Test
	public void testSigning() {
		//String key = buildHmacSignature("NM00860SU3g0ow6OQeo45p0KZp_Yx1HCicjas81PAY5V3Ex0o=","secret");
		String expected = "D7B4467AB4F25381AA731091148405EA2E20E184E4402CB3550F58705A70B9FC15FDF996673C3E8494DBBE094AFDC7E5F84D0544D2E09C18EB5F039D1198AC8E";
		//Sha512Impl impl = new Sha512Impl();
		String key = Sha512Impl.getSHA512("NM00860SU3g0ow6OQeo45p0KZp_Yx1HCicjas81PAY5V3Ex0o=");
		assertEquals(expected, key.toUpperCase());
	}
	
}
