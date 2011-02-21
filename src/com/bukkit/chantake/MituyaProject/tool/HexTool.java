/*
 * This file is part of the OdinMS Maple Story Server Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc> Matthias Butz <matze@odinms.de> Jan Christian Meyer <vimes@odinms.de> This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation version 3 as published by the Free Software Foundation. You may not use, modify or distribute this program under any other version of the GNU Affero General Public License. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.bukkit.chantake.MituyaProject.tool;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HexTool {
	private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/*
	 * public static String toStringFromAscii(byte[] bytes) { char[] ret = new char[bytes.length]; for (int x = 0; x < bytes.length; x++) { if (bytes[x] < 32 && bytes[x] >= 0) { ret[x] = '.'; } else { int chr = ((short) bytes[x]) & 0xFF; ret[x] = (char) chr; } } return String.valueOf(ret); } public static String toPaddedStringFromAscii(byte[] bytes) { String str = toStringFromAscii(bytes); StringBuilder ret = new StringBuilder(str.length() * 3); for (int i = 0; i < DoubleByteLanguage.getlength(str); i++) { ret.append(str.charAt(i)); ret.append("  "); } return ret.toString(); }
	 */
	public static byte[] getByteArrayFromHexString(String hex) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int nexti = 0;
		int nextb = 0;
		boolean highoc = true;
		outer: for (;;) {
			int number = -1;
			while (number == -1) {
				if (nexti == hex.length()) {
					break outer;
				}
				char chr = hex.charAt(nexti);
				if ((chr >= '0') && (chr <= '9')) {
					number = chr - '0';
				} else if ((chr >= 'a') && (chr <= 'f')) {
					number = chr - 'a' + 10;
				} else if ((chr >= 'A') && (chr <= 'F')) {
					number = chr - 'A' + 10;
				} else {
					number = -1;
				}
				nexti++;
			}
			if (highoc) {
				nextb = number << 4;
				highoc = false;
			} else {
				nextb |= number;
				highoc = true;
				baos.write(nextb);
			}
		}
		return baos.toByteArray();
	}

	public static String hexSha1(String in) {
		return hashWithDigest(in, "SHA-1");
	}

	public static String hexSha512(String in) {
		return hashWithDigest(in, "SHA-512");
	}

	public static String toString(byte[] bytes) {
		StringBuilder hexed = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			hexed.append(toString(bytes[i]));
			hexed.append(' ');
		}
		return hexed.substring(0, hexed.length() - 1);
	}

	private static String hashWithDigest(String in, String digest) {
		try {
			MessageDigest Digester = MessageDigest.getInstance(digest);
			Digester.update(in.getBytes("UTF-8"), 0, in.length());
			byte[] sha1Hash = Digester.digest();
			return toSimpleHexString(sha1Hash);
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException("Hashing the password failed", ex);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Encoding the string failed", e);
		}
	}

	private static String toSimpleHexString(byte[] bytes) {
		return HexTool.toString(bytes).replace(" ", "").toLowerCase();
	}

	private static String toString(byte byteValue) {
		int tmp = byteValue << 8;
		char[] retstr = new char[] { HEX[(tmp >> 12) & 0x0F], HEX[(tmp >> 8) & 0x0F] };
		return String.valueOf(retstr);
	}
}
