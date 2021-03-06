/**
 * This file was created by Quorum Born IT <http://www.qub-it.com/> and its
 * copyright terms are bind to the legal agreement regulating the FenixEdu@ULisboa
 * software development project between Quorum Born IT and Serviços Partilhados da
 * Universidade de Lisboa:
 *  - Copyright © 2015 Quorum Born IT (until any Go-Live phase)
 *  - Copyright © 2015 Universidade de Lisboa (after any Go-Live phase)
 *
 * Contributors: ricardo.pedro@qub-it.com, anil.mamede@qub-it.com
 *
 *
 * This file is part of FenixEdu Treasury.
 *
 * FenixEdu Treasury is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Treasury is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Treasury.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.fenixedu.bennu.adapters;

import java.lang.reflect.Type;

import org.fenixedu.commons.i18n.LocalizedString;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

/**
 * GSON serialiser/deserialiser for converting LocalizedString objects.
 */
public class LocalizedStringAdapter implements JsonSerializer<LocalizedString>, JsonDeserializer<LocalizedString> {
    @Override
    public JsonElement serialize(final LocalizedString src, final Type typeOfSrc, final JsonSerializationContext context) {

        return src.json();
    }

    @Override
    public LocalizedString deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        if (json instanceof JsonArray) {
            return LocalizedString.fromJson(json);
        } else {
            JsonObject parsed;
            try {
                parsed = new JsonParser().parse(json.toString().replace("\\", "").replace("\"{", "{").replace("}\"", "}"))
                        .getAsJsonObject();
            } catch (JsonSyntaxException ex) {
                parsed = new JsonParser().parse(json.toString()).getAsJsonObject();
            }

            return LocalizedString.fromJson(parsed);
        }
    }
}