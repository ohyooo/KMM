//
//  Theme.swift
//  iosApp
//
//  Created by Christian Melchior on 01/10/2021.
//

import Foundation
import SwiftUI

// Credit: https://stackoverflow.com/a/56874327/1389357
extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}
