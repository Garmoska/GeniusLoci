import codecs
import re


def remove_bom(line):
    return line[3:] if line.startswith(codecs.BOM_UTF8) else line


# mask for searching files
def get_image_mask(id_pic):
    return f"{id_pic}_*.jpg"


def get_image_type(image_name):
    pattern_image_tp = re.compile(r"_\D_")
    image_tp_list = re.findall(pattern_image_tp, image_name)
    if len(image_tp_list) == 1:
        return image_tp_list[0].replace("_", "")
    else:
        return ""
