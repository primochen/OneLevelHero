/* Copyright (c) 2014 PixelScientists
 *
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.mygdx.enums;

/**
 * @author Horace Velmont
 */
public class WeaponEnum {
	public enum WeaponType {
		one_handed_sword("one_handed_sword"), two_handed_sword("two_handed_sword"), book("book"), bow("bow"), staff(
				"staff");
		private String weaponType;

		private WeaponType(String weaponType) {
			this.weaponType = weaponType;
		}

		@Override
		public String toString() {
			return weaponType;
		}
	}

	public enum AttackType {
		smash("smash"), arrow("arrow"), slash("slash"), stab("stab"), slash_and_stab("slash_and_stab");
		private String attackType;

		private AttackType(String attackType) {
			this.attackType = attackType;
		}

		@Override
		public String toString() {
			return attackType;
		}
	}
}
