/*
 * EZPZ way 2 mk a ANDROID counter (0f numb3rs) + control it w/o having 2 use lots of boilerplate c0de :)
 * Copyright (c) SKYLAR BLEED, 2069
 * 
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, NOT BREAKING YOUR STUFF WITH THIS 
 * AND BREAKING COMPUTERS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * REGENTS OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * BREAKING YOUR SHIT; LOSS OF BRAIN CELLS WHILE CONTRIBUTING; 
 * OR ANNOYING, UNFIXABLE BUGS) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS AWFUL CODE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package com.example.nhwltrs.scoutapp2020;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/// A class that encapsulates two buttons and a text box
public class Counter {
    private int count = 0;
    public final Button inc, dec;
    public final TextView counter;

    /// Get count
    public int get_count() { return this.count; }

    /// Increment count
    public void inc(int a) {
        this.count += a;
    }

    /// Deincrement count
    public void dec(int a) {
        if (this.count < 1) return;
        this.count -= a;
    }

    public void setCount(int count) {
        this.count = count;
        this.counter.setText(String.format("%d",this.count));
    }

    /// Display count in this.counter
    public void display() {
        this.counter.setText(String.format("%d", this.count));
    }

    Counter(Button inc, Button dec, TextView counter) {
        // Setup incrementers
        this.inc = inc;
        this.dec = dec;
        this.counter = counter;

        // Add listeners to buttons
        // TODO remove boilerplate
        this.inc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inc(1);
                display();
            }
        });
        this.dec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dec(1);
                display();
            }
        });
    }
}